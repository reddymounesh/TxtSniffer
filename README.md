# Java TxtSniffer for Local Files

A simple multithreaded Java-based search engine that scans `.txt` files in a directory for a keyword. This tool supports:
- Case-insensitive search
- Optional stemming using the Porter Stemmer
- Concurrent scanning using multiple threads for faster results

## Features

- **Multithreaded search**: The search engine scans multiple files simultaneously to speed up the search process.
- **Keyword search**: Search for a keyword in `.txt` files in a directory.
- **Optional stemming**: Use the Porter Stemmer algorithm to reduce words to their root form (e.g., "running" → "run").
- **Ranking of results**: Files are ranked by the number of keyword matches found.
- **Performance stats**: Displays the total files scanned, total matches found, and time taken for the search.

## Prerequisites

- Java 8 or higher

## How to Run

1. **Clone the repository**:
   ```bash
   git clone https://github.com/reddymounesh/TxtSniffer.git
   cd java-search-engine

2. **complie the java files**:
```bash
javac TxtSniffer.java SnifferTask.java PorterStemmer.java

 #run the program
java TxtSniffer

## Follow the prompts
Enter the directory path where the .txt files are stored.

Enter the keyword to search for.

Choose whether to enable stemming or not.

## Sample Output
```bash
📁 Enter the directory path: /path/to/text/files
🔑 Enter the keyword to search: run
🧠 Enable stemming (yes/no): yes

🔍 [Thread] Scanning: file1.txt
✅ Match in file1.txt (Line 3): run
📄 Finished: file1.txt | Matches: 2

🔍 [Thread] Scanning: file2.txt
✅ Match in file2.txt (Line 1): running
📄 Finished: file2.txt | Matches: 1

🔎 Search Results (Ranked):
📄 file1.txt → 2 hits
📄 file2.txt → 1 hit

✅ Summary:
📂 Total files scanned: 5
🔢 Total matches found: 4
⏱ Time taken: 1.12 seconds
