# JDBC Registration System

This project is a demonstration of using JDBC with Oracle SQL on the USC Upstate servers. The primary goal of the project is to implement a registration system for classes and students within the school.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Requirements](#requirements)
- [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The purpose of this project is to allow for users to create, modify, and delete students information, create and delete courses, and also add grades for a school registrar system.

## Features

- **Class Registration**: Allows students to register for classes.
- **Student Management**: Manages student information and enrollment details.
- **Oracle SQL Integration**: Utilizes JDBC to interact with the Oracle SQL database on USC Upstate servers.

## Requirements

You will need to have access to an SQL server, this can be local if needed. The syntax might need to be changed in SQLCommands.java if you do not use Oracle SQL.

## Setup

The setup is simple, all that you need to do is create your own SQL server then use the SQL files to create tables in the database. Once this is done you simply change the connection in the driver to your connection. 

```bash
# Clone the repository
git clone https://github.com/your-username/your-repository.git

# Navigate to the project directory
cd your-repository

# Run setup script or commands
./setup.sh
