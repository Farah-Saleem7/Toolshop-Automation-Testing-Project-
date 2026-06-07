# Toolshop-Automation-Testing-Project-

# Toolshop E-Commerce — UI Automation Testing

End-to-end test automation framework for the Toolshop E-Commerce Platform, covering catalog navigation, product interactions, and account security.

[![Tests](https://img.shields.io/badge/tests-63-blue.svg)](#) [![Assertions](https://img.shields.io/badge/assertions-Multiple-orange.svg)](#) [![Pass Rate](https://img.shields.io/badge/pass%20rate-96.8%25-brightgreen.svg)](#) [![Runner](https://img.shields.io/badge/runner-Selenium%20%7C%20TestNG-red.svg)](#) [![License](https://img.shields.io/badge/license-MIT-green.svg)](#)

## Overview

A Java-based test automation framework that validates the Toolshop platform across three core resource modules (Catalog & Search, Product & Cart, and Framework & Security). The suite covers functional, negative, boundary, and security test cases—executing 63 test scenarios to safeguard web application stability and data integrity against UI regressions.

---

## Getting Started

### Prerequisites
* **Java** Development Kit (JDK 17 or higher)
* **Apache Maven** (v3.8+)
* **Google Chrome Browser**

## Project Structure

```text

├── main/java/main/             # Page Object Model (POM) classes
│   ├── BaseTest.java           # Driver setup & configuration
│   ├── CatalogPage.java        # Catalog UI elements & interactions
│   ├── ChangePasswordPage.java # Password modification page objects
│   ├── LoginPage.java          # Login page locators & logic
│   ├── ProductPage.java        # Product detail page objects
│   ├── ProfilePage.java        # User profile management objects
│   └── RegisterPage.java       # Registration form locators
├── test/java/test/             # Automated TestNG suites
│   ├── M1_CatalogSearchTests.java
│   ├── M2_ProductPageTests.java
│   ├── PasswordTests.java
│   └── SecurityTests.java
└── pom.xml                     # Maven dependencies

**Results**
+------------------------+-------------------+--------+---------+-----------------+
| Module Name            | Total Test Cases  | Passed | Blocked | Pass Rate (%)   |
+------------------------+-------------------+--------+---------+-----------------+
| Catalog & Search       | 23                | 23     | 0       | 100%            |
| Product & Cart         | 15                | 13     | 2       | 86.7%           |
| Framework & Security   | 25                | 25     | 0       | 100%            |
+------------------------+-------------------+--------+---------+-----------------+
| TOTAL                  | 63                | 61     | 2       | 96.8%           |
+------------------------+-------------------+--------+---------+-----------------+
















                 # Documentation
