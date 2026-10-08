# AlgoVision 2.0
## Unified DSA-Based Analytics Platform

---

## 1. Project Overview

AlgoVision 2.0 is a Java-based command-line analytics platform designed to demonstrate the practical application of core Data Structures and Algorithms (DSA) to real-world optimization and analytics problems.

The platform integrates multiple DSA concepts into a modular system and applies them across three major sectors:

- Logistics
- E-Commerce
- Fintech

The project provides a CLI-based interface through which users can ingest data, search records, perform optimization, analyze sector-specific data, and explore graph-based logistics networks.

---

## 2. Project Objective

The objective of AlgoVision 2.0 is to develop a unified analytics platform that combines fundamental Data Structures and Algorithms to solve practical problems related to data processing, searching, optimization, and network analysis.

The project demonstrates how theoretical DSA concepts can be integrated into a functional software system.

---

## 3. Key Features

- CLI-based user interface
- CSV-based data ingestion
- Queue-based shipment processing
- Hash-based record lookup
- Array-based catalog management
- Binary Search Tree-based searching
- Heap-based priority processing
- Greedy optimization
- Dynamic Programming optimization
- Graph-based logistics network analysis
- BFS and DFS traversal
- Shortest-path analysis
- E-Commerce inventory analytics
- Fintech transaction analytics
- Algorithmic complexity analysis

---

## 4. System Modules

### 4.1 Data Ingestion

The Data Ingestion module is responsible for reading logistics data from CSV files and processing shipment records.

Data Structures used:

- Queue
- Hashing

Applications:

- Maintaining incoming shipment records
- Processing shipments in order
- Fast shipment lookup

---

### 4.2 Cataloging

The Catalog module manages shipment records after they have been processed.

Data Structure used:

- Arrays

Applications:

- Storing shipment records
- Adding records
- Searching records
- Removing records
- Displaying catalog contents

---

### 4.3 Search Engine

The Search Engine provides efficient searching and priority-based processing.

Data Structures used:

- Binary Search Tree (BST)
- Heap

Applications:

- Searching shipment IDs
- Maintaining searchable shipment indexes
- Managing high-priority shipments
- Extracting the highest-priority shipment

---

### 4.4 Optimizer

The Optimizer module is used to determine efficient shipment loading strategies based on vehicle capacity and shipment weights.

Algorithms used:

- Greedy Algorithm
- Dynamic Programming

Applications:

- Shipment selection
- Vehicle capacity optimization
- Comparison of optimization approaches

---

### 4.5 Visualization and Graph Analysis

The Graph Engine represents the logistics network as a graph where:

- Cities represent vertices
- Routes represent edges
- Distance represents edge weight

Algorithms used:

- Breadth First Search (BFS)
- Depth First Search (DFS)
- Dijkstra's Shortest Path

Applications:

- Exploring logistics networks
- Route traversal
- Finding efficient routes between locations

---

### 4.6 Sector Analytics

The Sector Manager provides analytics for different business sectors.

#### E-Commerce

The E-Commerce module reads product data from CSV and calculates:

- Product inventory
- Total inventory value
- Highest-stock product

#### Fintech

The Fintech module reads transaction data from CSV and calculates:

- Total transaction volume
- Largest transaction
- Number of successful transactions

---

## 5. Supported Sectors

### Logistics

The logistics module uses shipment information such as:

- Shipment ID
- Source
- Destination
- Distance
- Weight
- Priority

It demonstrates queue processing, searching, optimization, and graph analysis.

### E-Commerce

The E-Commerce module analyzes:

- Product ID
- Product name
- Category
- Price
- Stock

It can calculate the total inventory value and identify products with the highest stock.

### Fintech

The Fintech module analyzes:

- Transaction ID
- Source account
- Destination account
- Transaction amount
- Transaction status

It provides transaction-volume and transaction-status analytics.

---

## 6. Project Structure

```text
AlgoVision 2.0/
│
├── src/
│   ├── Main.java
│   ├── CLI.java
│   ├── DataIngestion.java
│   ├── Catalog.java
│   ├── SearchEngine.java
│   ├── Optimizer.java
│   ├── GraphEngine.java
│   ├── SectorManager.java
│   └── Utils.java
│
├── data/
│   ├── logistics.csv
│   ├── ecommerce.csv
│   └── fintech.csv
│
└── docs/
    ├── README.md
    └── Complexity_Report.md