/* *****************************************************************************
 *  Name: Mohammad Alqudah
 *  NetID: malqudah
 *  Precept: P05
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Hours to complete assignment (optional):
 * 6
 **************************************************************************** */

Programming Assignment 2: Deques and Randomized Queues


/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
randomized queue: for the randomized queue, i used the array data structure;
this is because of the performance requirements listed in the checklist in
which the requirement for non iterator operations was constant amortized
time, which corresponded to using the resizing array data structure and using
less space overall.
for the implementation, i set an array of items (casted) and a number of
elements as instance variables for the class; n would keep track of whether
the queue was empty/# of items/etc. to enqueue an item, first check if
there was enough space in the array of items; if there wasnt, call resize and
double the size of the array (copied from textbook). afterwards add the item
accordingly. for dequeue, generate a random number in the range of 0, n with
std random and use that as an index for the array; return that and then
make sure to avoid loitering by setting the accessed array element to null;
also made sure to use resize to make the array shorter if n was
equal to 1/4 the original array length, to save space
sample used a similar logic except without accounting for loitering
because no element was removed
for the iterator, i created a defensive copy of the array; this allows
the original array to not be modified when calling shuffle, to randomize the
order of the queue. for has next and next, checks were done to see if theyexist;
then returned proper value of copy at given index i (specified as instance
variable). proper tests were then ran in main to make sure the proper
number of elements were being enqueued and dequeued or sampled, along
with testing all of the methods and printing their results

deque: for the deque class, i used the doubly linked list implementation
because the checklist said it needed to have constant worst case time, which
is the case with the doubly linked list implementation, although it uses
extra time and space because of the links.
started off using the private node class to begin the linked list; also added
a reference to not only next but also previous, since it is a doubly linked
list. used n to keep track of all the elements. isempty and size() self
explanatory; similar to randomized queue. for adding an item to the front,
there were two cases to consider: if the size of the list was 0 (ie we were
adding the very first element) and every other case; if it was the first element
it was important to keep note of the null references; ie set first = last and
make sure everything else points to null because there was only one element.
if the deque was not empty, then move up everything in the linked list by one
and adjust first.prev/first.next accordingly, essentially shifting the entire
list and adding something at the front (new first). similar logic applied to
add last; if it was the first item being added, then call addfirst and do
those commands, making sure to break the loop so as to not accidentally
incrememnt n twice as it was already incrememnted when we called addfirst. else,
shift everything in the linked list back essentially; then add a new last
and adjust the pointers accordingly, making sure to refer to null spaces as null
for removefirst, basically remove the first pointer and adjust the null values
accordingly; similarly for removelast, remove the literal last value and
adjust the pointers with the null values accordingly. lastly, for the iterator
i created a node current, which was initialized to point to first. hasnext
checked for  a null value in current.next, while next returned the item at
the literal next node. the check method was taken from linked stack and used
for reassurance (instructor said it wouldn't hurt to keep it in there).
the required testing was then done in main and printing respective results.



/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  40n  bytes
for randomized queue, there is the array of objects to account for as
well as the iterator; the array of objects uses 32n bytes worst
case, along with the iterator which uses 8n bytes, for a total of 40n bytes

Deque:              ~  48n  bytes
for deque, there is the Node creation which uses 40n bytes, and the
iterator which accounts for 8n bytes, for a total of 48n bytes.




/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */


/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
Tim from office hours, Nuranjin,Ryan Lab TAs



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */



/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
   When i was testing both of the classes, specifically with the for-each loops,
   an extra comma kept printing at the end of the data set that was outputted;
   i know how to fix this in a for loop but is there a way to fix this
   in a for-each loop?
