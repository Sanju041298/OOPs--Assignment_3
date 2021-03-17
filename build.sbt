name := "UserManagementSystem"

version := "0.1"

scalaVersion := "2.13.3"

lazy val userManagementSystem = project.in(file("UserManagementSystem"))

lazy val root = project.in(file(".")).aggregate(userManagementSystem)


