┌─────────────────────────────────────────────────────────────────────────┐
│                   LibraryManagementSystem                               │
├─────────────────────────────────────────────────────────────────────────┤
│ - books: Map<String, Book>                                              │
│ - patrons: Map<String, Patron>                                          │
│ - borrowRecords: Map<String, BorrowRecord>                              │
├─────────────────────────────────────────────────────────────────────────┤
│ + addBook(), removeBook(), updateBook(), searchBooks()                  │
│ + addPatron(), updatePatron(), getPatron()                              │
│ + borrowBook(), returnBook(), getPatronBorrowHistory()                  │
│ + getOverdueBooks(), displayLibraryStatus()                             │
└─────────────────────────────────────────────────────────────────────────┘
         │              │              │
         ▼              ▼              ▼
    ┌────────┐     ┌────────┐    ┌──────────────┐
    │  Book  │     │ Patron │    │ BorrowRecord │
    ├────────┤     ├────────┤    ├──────────────┤
    │- isbn  │     │- id    │    │- recordId    │
    │- title │     │- name  │    │- patronId    │
    │- author│     │- email │    │- isbn        │
    │- year  │     │- hist[]│    │- borrowDate  │
    │- copies│     └────────┘    │- dueDate     │
    └────────┘                    │- returnDate  │
                                 └──────────────┘

┌──────────────────────────────────┐
│  LibrarySearchStrategy           │
│  (Interface)                     │
├──────────────────────────────────┤
│ + search(books, query): List     │
└──────────────────────────────────┘
    ▲           ▲           ▲
    │           │           │
    │           │           │
    ▼           ▼           ▼
┌─────────┐ ┌─────────┐ ┌──────┐
│ Title   │ │ Author  │ │ ISBN │
│Strategy │ │Strategy │ │Strat.│
└─────────┘ └─────────┘ └──────┘

┌──────────────────────────────┐
│ SearchStrategyFactory        │
├──────────────────────────────┤
│ + createStrategy(type)       │
└──────────────────────────────┘
