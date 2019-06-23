# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# List of code smells

## Code Smell 1: [Duplicate Code]

### Code Smell Category: [Dispensables]

### List of classes and line numbers involved:

* [class Bubble line 82-135]

### Description:

[methods floatStraightUp(),floatLeftUp(), floatRightUp() have same parts of code (line 89-96, 107-114, 125-132) changing appearance of bubble.]

### Solution:

[Extract Method, define a new method called changeAppearance() which change appearance (same as the duplicated part line line 89-96).
And calls for method changeAppearance() in these 3 methods  instead of the duplicated part right after updating x and y.]

### Explanation

[Since these three methods all need to change the appearance of bubble with same trunk of code, I extract this part to make a new method. Now I only need to call
for the new method inside these three methods. This reduce duplicates and simplifies code structure, also the code becomes more readable.]

============================================================

## Code Smell 2: [Switch Statements]

### Code Smell Category: [Object-Orientation Abusers]

### List of classes and line numbers involved:

* [class FishFrame line 32-40]

### Description:

[Sequence of if statements doing same thing, calling method draw() of each of class Fish(), Bubble(), HungryFish(), Seaweed()
.]

### Solution:
[Define a class tankObject as super class of class Fish, Bubble, HungryFish, Seaweed, let these 4 classes extends class tankObject.
Define an abstract method draw() inside class tankObject and let these 4 classes to implement draw() for themselves specifically.
To replace the sequence of if statements, we just need  a single instanceof check, if the current item in FishTank.myLittleFishies is 
instance of tankObject then we call for its draw() method, no matter what type of tankObject it is, it will call its draw() method.]


### Explanation

[Removing duplicated sequence of if statements by defining an abstract super class, we now only need to do instance check once and can call at each subclass's
specific draw() method. Since class Fish, Bubble, HungryFish, and Seaweed are all subclasses of tankObject and they all implemented their
specific draw() method, we successfully fulfill function of original if sequence with less code and simpler code structure.]
============================================================

## Code Smell 3: [Shotgun Surgery]

### Code Smell Category: [Change Preventers]

### List of classes and line numbers involved:

* [class FishFrame line 32-40, class FishTank line 72-85]

### Description:

[If I want to add a new class, for example turtle, into the fish tank, I have to write a specific class
for turtle. And I need to add additional if statements to check whether this object is turtle in these two
sequence of if statements. Making this modification requires that me to make many small changes to many different 
classes.]

### Solution:

[Make an class tankObject() as super class of class Fish(), Bubble(), HungryFish(), Seaweed()
 and of any possible new classes that I might want add to, write an abstract method for doing specific things that let those subclasses to implement for themselves specifically. To replace line 32-40 and line 72-85, check if the current array item is an instance of tankObject()
 if so call for its  method. Now if I want to add another subclass of tankObject, I simply let it extends tankObject and implement its
 constructor, and other methods... I don't need to check which specific type of tankObject subclass it is in these two 
 sequence of if statements.]

### Explanation

[By doing this, when I want to add another object to fishtank, I only need to define a new class that extends this
class tankObject and implement its own specific methods, I do not need to add any other codes specifically for this new class in other classes such as FishTank and FishFrame.]

============================================================

## Code Smell 4: [Dead Code]

### Code Smell Category: [Dispensables]

### List of classes and line numbers involved:

* [class FishTank line 10,12]

### Description:

[The code defines two variables charWidth and charHeight which are never used.]

### Solution:

[Delete line 10 and 12]

### Explanation

[Simply remove these two unused parameters would eliminate dead code and simplify code size.]

============================================================

## Code Smell 5: [Comments]

### Code Smell Category: [Dispensables]

### List of classes and line numbers involved:

* [class Seaweed line 49,53,57,63]

### Description:

[Comments explaining unnecessary complicated expression in if statements when deciding leaning of seaweed.]

### Solution:

[Simplifies the expression, check odd or even by the expression (i%2 == 0) and check leaning by the expression (lR),
merge separated if statements into one big if and elseif statements, with two branches in each section. Delete comments.]

### Explanation

[Now since the if statements are very clear that we have 4 cases where we check odd or even and lR or !lR, we don't
need any explanatory comments anymore. The code is self-explanatory and structure is clearer.]

============================================================
