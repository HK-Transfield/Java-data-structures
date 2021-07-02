====Hashing Techniques=====
@HK Transfield


----CKey Hashcode----
    int hashCode = 0;
    for (int i = 0; i < keystring.length(); i++) 
        hashCode = hashCode * 31 + keystring.charAt(i);

To convert the keystring into an integer value I take current value of the hashcode, multipliy it by the 
odd prime number 31, and then adds the ASCII character value of the keystring each iteration through the loop.
The other techniques I've trialed include digit extraction


----Modulo division----
    int hash = hashCode % capacity;

    capacity: 4500 count: 501  load: 0.11 probes: 1084 expected: 1.06 actual: 2 average: 2.16
    capacity: 2000 count: 501  load: 0.25 probes: 1151 expected: 1.13 actual: 1 average: 2.30
    capacity: 1000 count: 501  load: 0.50 probes: 1491 expected: 1.25 actual: 5 average: 2.98

Modulo division, without any further techniques implemented in the hashcode() method, performed 
the worst resulting in the highest average and actual probe count for get() and put() operations,
averaging nearly 3 probes for get()/put() operations on a single CData.


----Digit extraction----
    hashCode =
        ((hashCode / 1000000000 * 1000000) +  
        (hashCode / 100000000 % 10 * 100000) + 
        (hashCode / 10000000 % 10 * 10000) + 
        (hashCode / 1000000 % 10 * 1000) + 
        (hashCode / 100000 % 10 * 100) + 
        (hashCode / 1000 % 10 * 10) + 
        (hashCode % 10));

    capacity: 4500 count: 501  load: 0.11 probes: 1050 expected: 1.06 actual: 2 average: 2.10
    capacity: 2000 count: 501  load: 0.25 probes: 1127 expected: 1.13 actual: 1 average: 2.25
    capacity: 1000 count: 501  load: 0.50 probes: 1404 expected: 1.25 actual: 8 average: 2.80

The higher the table's capacity, the better digit extraction was able to minimise collisions. 
However, while the average for tables with smaller capacities was lower than other techniques, 
the actual number of probes in a get()/put() opertation being performed for a single CData greatly 
increased as the load of the table increased, although it maintained a lower total number of probes in 
comparison to other hashing techiques.


----Folding----
    hashCode = 
        ((hashCode / 100000000) + 
        (hashCode / 1000000 % 10000) + 
        (hashCode / 10000 % 10000) + 
        (hashCode / 100 % 10000) + 
        (hashCode % 10000));

    capacity: 4500 count: 501  load: 0.11 probes: 1052 expected: 1.06 actual: 2 average: 2.10
    capacity: 2000 count: 501  load: 0.25 probes: 1141 expected: 1.13 actual: 3 average: 2.28
    capacity: 1000 count: 501  load: 0.50 probes: 1449 expected: 1.25 actual: 3 average: 2.89

Folding had similar performance to digit extraction in minimising collisions in tables with greater 
capacities, however it performed worser in tables with smaller capacities.


----Conclusion----
All techniques, when inserting into a table with a smaller capacity, nearly tripled in the number 
of expected probes needed for the CHashtable operations, with modulo division (without any further
techiques) performing the worst.

I have found digit extraction to be the best performing technique I have tested in my hashCode()
method to minimise collisions, especially in high capacity tables, as shown by  having the lowest 
average and total probes when inserting the CData into the table.
