# UTEM Reporter — Cucumber + Selenium Integration Guide

**UTEM (Universal Test Execution Monitor)** is a real-time test reporting dashboard. This guide shows how to integrate the UTEM reporter into any existing **Cucumber + JUnit 4 + Selenium** Maven project so every test run appears live in the dashboard.

---

## Prerequisites

| Requirement | Version |
|-------------|---------|
| Java | 17 or later |
| Maven | 3.8 or later |
| Cucumber | 7.x |
| JUnit | 4.13.x |
| Selenium | 4.x (optional — for screenshot capture) |
| UTEM Server | Running at `http://localhost:8080` |

---

## Step 1 — Download the Reporter JAR

Download **`utem-reporter-junit5-0.1.0.jar`** from Google Drive:

[⬇ Download utem-reporter-junit5-0.1.0.jar](https://drive.google.com/file/d/1nhUYETlX0QPoyeXLhxsSb2LHpJTgcwcn/view?usp=sharing)

Place it in your project's local Maven repository at this exact path:

```
your-project/
└── repo/
    └── com/
        └── utem/
            └── utem-reporter-junit5/
                └── 0.1.0/
                    ├── utem-reporter-junit5-0.1.0.jar
                    └── utem-reporter-junit5-0.1.0.pom
```

Create the `.pom` file alongside the JAR with the following content:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.utem</groupId>
  <artifactId>utem-reporter-junit5</artifactId>
  <version>0.1.0</version>
</project>
```

---

## Step 2 — Configure `pom.xml`

### 2a. Register the local repository

```xml
<repositories>
    <repository>
        <id>local-repo</id>
        <name>Local Repository</name>
        <url>file://${project.basedir}/repo</url>
    </repository>
</repositories>
```

### 2b. Add the reporter dependency

```xml
<dependency>
    <groupId>com.utem</groupId>
    <artifactId>utem-reporter-junit5</artifactId>
    <version>0.1.0</version>
    <scope>test</scope>
</dependency>
```

### 2c. Configure Surefire with UTEM properties

Define defaults in `<properties>` and reference them in Surefire. Any property can be overridden at run time with `-D` on the command line.

```xml
<properties>
    <!-- Default values — override with -D on the command line -->
    <utem.server.url>http://localhost:8080/utem</utem.server.url>
    <utem.run.label></utem.run.label>
    <utem.job.name></utem.job.name>
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.2.5</version>
            <configuration>
                <systemPropertyVariables>
                    <utem.server.url>${utem.server.url}</utem.server.url>
                    <utem.run.label>${utem.run.label}</utem.run.label>
                    <utem.job.name>${utem.job.name}</utem.job.name>
                </systemPropertyVariables>
            </configuration>
        </plugin>
    </plugins>
</build>
```

---

## Step 3 — Register the Plugin in Your Test Runner

Add `UtemCucumberPlugin` to the `plugin` list in your `@CucumberOptions`:

```java
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.your.stepdefs", "com.your.hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber.html",
        "com.utem.reporter.junit5.UtemCucumberPlugin"   // <-- add this
    }
)
public class TestRunner {
}
```

---

## Step 4 — Register the WebDriver (Selenium only)

If you use Selenium, register your driver with UTEM so it can automatically capture a screenshot on test failure.

In your Cucumber `@Before` / `@After` hooks:

```java
import com.utem.reporter.junit5.WebDriverRegistry;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class MyHooks {

    @Before
    public void setUp() {
        // ... start your WebDriver ...
        WebDriverRegistry.register(driver);   // <-- register
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriverRegistry.unregister();       // <-- unregister
        // ... quit driver ...
    }
}
```

> **No Selenium?** Skip this step entirely — the reporter works without it.

---

## Step 5 — Run Your Tests

### Standard run (uses defaults from pom.xml)
```bash
mvn test
```

### Point to a different UTEM server
```bash
# PowerShell
mvn test '-Dutem.server.url=http://192.168.1.10:8080/utem'

# Bash / CMD
mvn test -Dutem.server.url=http://192.168.1.10:8080/utem
```

### With a job name (groups runs under one pipeline card in the dashboard)
```bash
# PowerShell
mvn test '-Dutem.job.name=Nightly Regression'

# Bash / CMD
mvn test -Dutem.job.name=NightlyRegression
```

### With a label (e.g. smoke, regression, release)
```bash
mvn test '-Dutem.run.label=smoke'
```

### Headless browser mode
```bash
mvn test '-Dheadless=true'
```

### All options combined
```bash
mvn test '-Dutem.server.url=http://myserver:8080/utem' '-Dutem.job.name=Booking' '-Dutem.run.label=smoke' '-Dheadless=true'
```

---

## Configuration Reference

| Property | How to set | Description |
|----------|-----------|-------------|
| `utem.server.url` | `pom.xml` or `-D` | UTEM server base URL (default: `http://localhost:8080/utem`) |
| `utem.run.label` | `pom.xml` or `-D` | Tag this run (e.g. `smoke`, `regression`) |
| `utem.job.name` | `pom.xml` or `-D` | Pipeline/job name for grouping runs |
| `headless` | `-D` flag | Set `true` to run Chrome in headless mode |
| `CI` env var | Shell env | Any non-empty value also enables headless mode |

---

## What You See in the Dashboard

After running, open `http://localhost:5173` to see:

- **Live run progress** — test cases and steps updating in real time via WebSocket
- **Run detail** — full hierarchy: feature → scenario → step, with pass/fail status and duration
- **Failure screenshots** — automatically captured and attached when a step fails (Selenium only)
- **Jobs view** (`/jobs`) — all runs grouped by job name with pass rate history
- **Trends** — pass rate, duration, and flakiness over time
- **Export** — download any run as JSON, CSV, JUnit XML, or PDF

---

## Project Structure (this repository)

```
src/test/java/com/utem/
├── base/           BaseTest.java          – WebDriver lifecycle (start/stop/headless)
├── hooks/          UTEMHooks.java         – Cucumber @Before/@After with UTEM registration
├── runner/         TestRunner.java        – @CucumberOptions entry point
├── stepdefs/       *Steps.java            – Step definition classes
└── pages/          *Page.java             – Page Object Model classes

repo/com/utem/utem-reporter-junit5/0.1.0/ – Local Maven repo for the UTEM JAR
```

---

## Troubleshooting

| Symptom | Fix |
|---------|-----|
| `[UTEM] Event rejected (HTTP 404)` | Check `utem.server.url` ends with `/utem`, not just the port |
| `[UTEM] Event rejected (HTTP 500)` | Make sure the UTEM server is running; restart it if needed |
| No run appears in dashboard | Confirm the plugin is listed in `@CucumberOptions(plugin = {...})` |
| Screenshot not attached | Ensure `WebDriverRegistry.register(driver)` is called in `@Before` |
| PowerShell `-D` property error | Wrap in single quotes: `'-Dutem.job.name=MyJob'` |
