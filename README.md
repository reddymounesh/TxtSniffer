# Java Search Engine on local files

A simple multithreaded Java-based search engine to scan `.txt` files in a directory for a keyword. Supports:
- Case-insensitive search
- Optional stemming using PorterStemmer
- Concurrent scanning using threads

## Run the project

```bash
javac SearchEngine.java SearchTask.java PorterStemmer.java
java SearchEngine
