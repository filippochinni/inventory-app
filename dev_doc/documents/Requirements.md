# Requirements Document - InventoryApp

Date: 2026-07-17

Version: v1 - Requirements Analysis for InventoryApp

| Version | Curr App Version | Curr Milestone | Change | 
|:-------:|:-----------------|:---------------|:-------|
|   v1    | v0.1.0-prealpha  | Requirements   | -      |

#### Contents

- [Requirements Document - InventoryApp](#requirements-document---inventoryapp)
	* [Informal Description](#informal-description)
	* [Business Model](#business-model)
	* [Stakeholders](#stakeholders)
	* [Requirements Elicitation](#requirements-elicitation)
		+ [Personas](#personas)
		+ [Problems](#problems)
		+ [User Stories](#user-stories)
	* [Context](#context)
		+ [Actors](#actors)
		+ [Actors Interfaces](#system-access-interfaces)
		+ [Context Diagram](#context-diagram)
	* [Requirements](#requirements)
		+ [Functional Requirements](#functional-requirements)
			- [Table of Rights](#table-of-rights)
		+ [Non-Functional Requirements](#non-functional-requirements)
	* [Use Cases](#use-cases)
		+ [Use Cases Diagram](#use-cases-diagram)
		+ [Use Cases List](#use-cases-list)
			- [Use case 1 (UC1)](#use-case-1-uc1)
				* [Scenario 1.1](#scenario-11)
				* [Scenario 1.2](#scenario-12)
			- [Use case 2 (UC2)](#use-case-2-uc2)
				* [Scenario 2.1](#scenario-21)
				* [Scenario 2.2](#scenario-22)
			- [Use case 3 (UC3)](#use-case-3-uc3)
				* [Scenario 3.1](#scenario-31)
				* [Scenario 3.2](#scenario-32)
	* [System Design](#system-design-diagram)
	* [Deployment Diagram](#deployment-diagram)
	* [Glossary](#glossary)
		+ [Glossary Terms](#glossary-terms)
		+ [Glossary Diagram](#glossary-diagram)

## Informal Description

InventoryApp is a stock management (inventory, stock, stocktaking) Android application which keeps track of the location of items and provides multiple features to help find, organize and visualize items.

## Business Model

There are multiple possible business models for InventoryApp.

__Open Source__: InventoryApp could be released as an open-source project, publicly available and free to use and modify. With this model financial support could come from donations, enterprise licensing, paid technical support, hosting.

__Proprietary Software__: InventoryApp could be released as a standard proprietary software, obtainable through a one-time purchase.

__Software as a Service (SaaS)__: InventoryApp could be released as a subscription-based service, with a monthly or yearly fee to use the app.

__Freemium__: InventoryApp could be released as a free app with basic features, and lock the full functionality behind a paywall, with a one-time purchase or a subscription model.

__Adware__: InventoryApp could be released as a free app with and display ads to the user, with the possibility of removing ads through payment, combining with other business models.

__Commissioning__: InventoryApp could be proposed to a particular client while in early development, and be commissioned to be developed ad-hoc for that particular client.

## Stakeholders

| Stakeholder         | Description                                                                                                                                                |
|:--------------------|:-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| User (Generic)      | Private citizen who wishes to catalogate their personal proprieties to whatever purpose (selling, separation of propriety, stocktaking, house moving)      |
| Small Businesses    | Owners of small commercial activities, like stores, who need a digitalized solution to manage their inventory and catalogate their goods                   |
| Private Companies   | Companies with a Logistic Division, or in general with logistic necessities who needs a pocket solution usable by any worker at any time in the warehouses |
| Compulsive Hoarders | Individuals who collect large quantities of unrelated items and need a way to categorize and track them                                                    |
| Collectors          | Individuals who collect items of interest and need a way to organize their collections either for tidiness or for future selling                           |
| Payment Service     | (If the App requires payment processing) The payment service for the purchase of the App license                                                           |

## Requirements Elicitation

Information collected informally during the Concept phase of the project. In this section such intel is organized and partially formalized so that it can be used in the rest of the Requirements Engineering process.

### Personas

__Persona 1 - "Nonno"__:
Man, Old, 93, Retired

He owns an assorted collection of objects, large and small, tools and utensils, furniture and machinery, basically everything. He needs a fast way to track and catalog his items.

__Persona 2 - "Filippo"__:
Man, Young, 24, Student

He has recently found his old collection of toys, soon he will have to move to a new apartment. He needs a way to catalog his items distinguishing between personal items, toys, clothes, etc.

__Persona 3 - "Maria"__:
Woman, Adult, 56, Professor

She has a large collection of books, and she needs a way to catalog them and keep track of their location in her house.

__Persona 4 - "Miriam"__:
Woman, Adult, 40, Housewife

She owns a large amount of clothes from her family, both old and currently in use. She wants to free the house from old stuff and an easy way to track and catalog clothes and other junk to be sold or donated.

__Persona 5 - "John"__:
Man, Adult, 35, Warehouse Worker

He works in a warehouse and his tired to be handled a printed list of items to be stocked and moved. He would prefer a smarter solution.


### Problems

| Problem                 | Description                                                                                                 |
|:------------------------|:------------------------------------------------------------------------------------------------------------|
| Large amount of Items   | Some people own large amounts of assorted items                                                             |
| Cannot find belongings  | People often forget where they put their things                                                             |
| Unorganized Items       | Items are often stored without a precise orden an without any form of labeling                              |
| Paper notes Stocktaking | Inventories written on pieces of paper can easily be lost or forgotten                                      |
| Stocktaking is Boring   | Many people do not like writing inventories, mainly because is a slow process                               |
| Decentralized Inventory | Both in personal and professional settings an Inventory is tied to a location and has a single access point |
| Lack of Info on Items   | Most existing stocktaking systems for small and medium stores only store the item name                      |

### User Stories

| User Story ID | User Story Description |
|:--------------|:-----------------------|
| US1           | __As a__               |                
|               | __I want to__          |              
|               | __So that__            |
| ------------- | ---------------------- |
| US2           | __As a__               |                
|               | __I want to__          |              
|               | __So that__            |
| ------------- | ---------------------- |

## Context

### Actors

| Actor Name | Description |
|:-----------|:------------|

### System Access Interfaces

| Actor | Logical Interface | Physical Interface |
|:------|:-----------------:|:------------------:|

### Context Diagram

## Requirements

### Functional Requirements

| ID | Description |
|:---|:------------|

#### Table of Rights

| FR | Actor |
|:--:|:-----:|

### Non-Functional Requirements

The possible types of Non-Functional Requirement are the following: Correctness, Usability, Efficiency, Reliability, Maintainability, Portability, Security, Safety, Interoperability, Ethical, Standard, Privacy, Domain.

| ID | Type | Description | Refers to |
|:--:|:-----|:------------|:---------:|

## Use Cases

### Use Cases Diagram

### Use Cases List

#### Use case 1 (UC1)

| UC1              | Use Case 1: Use Case Name | 
|:-----------------|:--------------------------|
| Actors Involved  |                           |
| Pre-condition    |                           |
| Post-condition   |                           |
| Nominal Scenario |                           |
| Variants         |                           |
| Exceptions       |                           |

##### Scenario 1.1

| UC1 - S1.1     | Scenario 1.1: Use Case Name (Scenario Spec) |
|:---------------|:--------------------------------------------|
| Pre-condition  |                                             |
| Post-condition |                                             |
| __Step#__      | <div align="center"> __Description__ </div> |
| 1              |                                             |
| 2              |                                             |
| 3              |                                             |
| 4              |                                             |

##### Scenario 1.2

#### Use case 2 (UC2)

##### Scenario 2.1

##### Scenario 2.2

#### Use case 3 (UC3)

##### Scenario 3.1

##### Scenario 3.2

## System Design Diagram

## Deployment Diagram

## Glossary

### Glossary Terms

### Glossary Diagram

