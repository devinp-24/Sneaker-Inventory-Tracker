# My Personal Project

## Shoe Inventory Tracker

I started collecting shoes since I became a teenager.
From my knowledge, a lot of shoes are reminiscent of
something such as the first sneaker of a specific model
that was created or a special moment in the career of
a particular athlete or player.

### What will the application do?

My project is a shoe inventory tracker for users like
me who love collecting shoes. I aim to create an app
that can do the following:

#### User story

- As a user, I want to be able to add new shoes to my
collection
- As a user, I want to be able to indicate which shoes
I am willing to sell and at what price
- As a user, I want to be able to indicate which shoes
I am willing to keep in my personal collection
- As a user, I want to be able to get a list of shoes
currently in my collection
- As a user, I want to be able to remove shoes from
my collection
- As a user, I want to be able to check the number of shoes I have sold before
- As a user, I want to be able to add shoes to a wishlist
- As a user, I want to be given an option to save my
shoe collection in a file for later use
- As a user, I want to be able to be able to load my
shoe collection from a file

An example of text with **bold** and *italic* fonts. 

### Who will use it?

Like I mentioned, this application can be used by other
***'sneakerheads'*** (people who like collecting shoes) to 
keep track of their collections and also by *people 
who sell shoes* to keep track of what they have in their
inventories.

### Why is this project of interest to you?

I have been collecting sneakers for years now and since
I now own a lot of shoes, it is difficult to keep track
of each and every shoe I have. Therefore, to keep an
organized tab of my shoe collection, I felt like this
project was a very interesting idea. Using this app,
it'll be easier to show other people your collection or
to navigate if you have a shoe you're willing to sell
that other people might purchase.

### Instructions for Grader

- You can generate the first required event related 
to adding Xs to a Y by first creating a new collection or
loading previous shoe collection and then choosing the
add shoe option from the menu pane and filling in the
shoe details and clicking the Add button to add a new Shoe
to the Shoe Inventory
- You can generate the second required event related 
to adding Xs to a Y by clicking on 'Shoes I Keep Collection'
in the menu pane of the menu page to view a subset of shoes
that the user wishes to keep in personal collection
- You can locate my visual component by running the application
and there is an image of shoes along with the title on the
welcome page. There is an image on the Menu Pane as well
which can be seen when the use chooses to create a new 
shoe collection or load a shoe collection from the welcome
page
- You can save the state of my application by clicking on
the 'Save Collection' option in the menu pane of the menu
page. A pop-up message indicates when collection is saved
- You can reload the state of my application by clicking on
the 'Load My Shoe Collection' button in the welcome page.
A pop-up message shows if data is loaded successfully.

### Phase 4: Task 2

Sample 

Thu Dec 01 13:19:14 PST 2022\
'Jordan 1 Travis' added to Shoe Collection\
Thu Dec 01 13:19:14 PST 2022\
'Yeezy 700V3' added to Shoe Collection\
Thu Dec 01 13:19:14 PST 2022\
'Triple S' added to Shoe Collection\
Thu Dec 01 13:19:14 PST 2022\
'B23' added to Shoe Collection\
Thu Dec 01 13:19:14 PST 2022\
'Dunk Panda' added to Shoe Collection\
Thu Dec 01 13:20:10 PST 2022\
'Jordan 1 Shattered Backboard' added to Shoe Collection\
Thu Dec 01 13:20:24 PST 2022\
'Dunk Panda' removed from Shoe Collection\
Thu Dec 01 13:20:32 PST 2022\
'B23' removed from Shoe Collection\
Thu Dec 01 13:20:32 PST 2022\
'B23' was sold\
Thu Dec 01 13:20:59 PST 2022\
'Jordan 1 Spider-man Origin' added to Wishlist

Note: This is a sample representation of the log events
printed on console after the application is closed. Events
that are logged are: when a shoe is added to the collection, 
when a shoe is removed from the collection, when a shoe is 
sold, when a shoe is added to the wishlist. When a shoe 
is sold, two events occur: the shoe is first removed from 
the collection and then sold.

### Phase 4: Task 3

My UML diagram in the root folder represents the 
UML class diagram of the entire project except
the test classes. I have used solid line arrows
for direct associations and have included the
multiplicities. To briefly summarize my diagram:

- EventLog class has a collection of events so
direct association with 0..n multiplicity
- ShoeInventory and Shoe classes implement the
Writable interface
- ShoeInventory has a collection of shoes therefore
direct association with 0..n multiplicity
- ShoeInventory uses an EventLog instance therefore
aggregation relationship
- JsonReader and JsonWriter both use the ShoeInventory
class for saving and loading therefore direct association
- JsonReader and JsonWriter both use the Shoe
class for saving and loading therefore direct association
- WelcomePage class uses one JsonReader object and one
ShoeInventory object therefore has direct relationship with
both with a multiplicity of 1
- WelcomePage class uses one JsonWriter object and one
ShoeInventory object therefore has direct relationship with
both with a multiplicity of 1
- WelcomePage also calls the MenuPage to open it therefore
direct association
- MyShoeInventoryApp (old class to run the console app) 
creates one JsonWriter object, one JsonReader object, one
ShoeInventory object and therefore has direct association
with all with multiplicity of 1
- The MainGUI creates new WelcomePage so direct association
- The Main class creates new MyShoeInventoryApp therefore
direct association with 1 multiplicity

Finally, in my opinion, the project was a great way
of learning how to organize my code. If I did have more
time to complete my project, however, the first thing 
I would change is my GUI class. Most of my methods
are very long and contain the entire formatting.
If I had time I would organize the code better into 
more methods and classes to make the code more 
comprehensible. Moreover, I would have added more
features like images for shoes and an entire database
of the common shoes so users could select shoes
to add with pre-filled information instead of having
to type in the information themselves. I also would
have worked on my GUI more and made the application
easier to navigate and use for the user.