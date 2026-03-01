# Library Access Control with Semaphores

## Author
Luis Fernando Monjaraz Briseño

## Description
This Java program demonstrates the use of Semaphores for controlling access to a shared resource (a library with limited capacity). The system implements a real-world scenario where maximum occupancy must be enforced.

## Implementation

### library_semaphore_access.java
- Uses `Semaphore` for capacity management
- Controls maximum number of concurrent visitors
- Implements fair FIFO access (using true parameter in Semaphore constructor)
- Tracks visitors currently inside the library
- Simulates visit duration with random delays (2-6 seconds)

## Features
- **Semaphore Control**: Limits concurrent access to shared resource
- **Fair Queuing**: FIFO order for waiting visitors
- **Capacity Management**: Enforces maximum occupancy rules
- **Real-time Monitoring**: Displays library status and visitor count
- **Visual Feedback**: Status indicators for full/available capacity

## Key Concepts
- **Semaphores**: Counting semaphore for resource access control
- **acquire()**: Wait for and acquire a permit
- **release()**: Release a permit back to the semaphore
- **tryAcquire()**: Non-blocking attempt to acquire a permit
- **Thread Synchronization**: Coordinating access to shared resource

## How to Compile and Run
```bash
javac library_semaphore_access.java
java library_semaphore_access
```

## Requirements
- Java 5 or higher
- JVM (Java Virtual Machine)

## License
Educational project
