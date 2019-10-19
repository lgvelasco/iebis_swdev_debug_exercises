# iebis_swdev_debugging
Source code to test debugging

## Instructions
First, **Fork** this project.

There are three exercises split into three branches of this repository. You must switch branches to checkout the code of each exercise.
Then, find the bugs that appear in each branch.
Fix the bugs if you can and answer to the questions proposed below.
Commit the code before checking out a different branch to avoid losing the fixes that you have made to the code.

Once that you are done fixing bugs, **to score you must**:
1. Switch to the master branch.
2. Type below in this README.md file the answer to each question and paste some code that you have used to solve them.
3. Commit the changes
4. Push to your GitHub repository
5. **Finally place a Pull Request so I can see your proposed answers**


## Exercises
### Exercise 1
In this code, there is a class called WordAnalyzer that contains several methods that analyzes some characteristics of the word passed as an argument when the object WordAnalyzer is created.

For some reason, the methods are not working properly, sometimes they return the correct value and others don't. You need to answer the next questions.

#### Why the method _firstMultipleCharacter_ is returning "c" for the word _comprehensive_, when the correct answer should be "e"?
This is because the position in the _find_ method was the same as the one in _firstMultipleCharacter, meaning that the program was checking if a character was equal to itself. Hence, "c" by being the first character in the word was being checked if it was equal to itself, which was true. 

#### Why the method _firstRepeatedCharacter_ is throwing an exception?
Because the method is going beyond the size of the String, it is looking for a character in a position greater than the string itself. To solve this it is required to limit _i_ to be one less than the actual length of the string. 
```java 
for (int i = 0; i < word.length()-1; i++)
```

#### Why the method _countGroupsRepeatedCharacters_ returns 3 in one case when it should be 4?
That is because it wasn't taking into consideration the first group of repeated characters, as the loop started in position 1. I added an if statement to check if the character in position 0 equals the one at position 1. 
```java
if (word.charAt(0) == word.charAt(1)) {
            c++;
        }
```

**Strategy**: Place breakpoints before the methods are executed, step into them and see what happens.


### Exercise 2
In this code, we are placing mines in a board game where we have several spaces that can be mined. 
The boards can contain _Element_ objects, and since _Space_ and _Mine_ inherits from _Element_, the boards can contain this kind as well.

We have two boards of different sizes and place a different number of mines on each one. But in the second case, it takes longer to place all the mines.

#### Why placing fewer bombs takes longer in the second case?
Even though the blue board is smaller and has one less bomb, the difference between spaces and bombs is far smaller compared to the red board. This means that the program has to generate a random number that has yet no been occupied by a bomb, and as more bombs are placed there are fewer available spaces. For example, for the last bomb in the blue board, there are only 2 available spaces, but the program will have to find it using random numbers that go up to 1,500,000; meaning that there is only a 0.000133% of finding those two spaces. 

#### Knowing that usually there are going to be more bombs than spaces in the final boards, how would you change the method _minningTheBoard_ to be more efficient?
If there are gonna be more bombs than spaces it makes sense to first fill the board with bombs and later add the spaces. Depending on the ratio of bombs:spaces the code will decide if it is better to _miningTheBoard_ or to _spacingTheBoard_

```java
if(ratio <= 0.5) {
            for(int i = 0; i < size; i++) {
                myBoard.put(i, new Space());
            }
            // Adds the mines
            minningTheBoard(bombs);
        } else {
            for(int i = 0; i < size; i++) {
                myBoard.put(i, new Mine());
            }
            // Adds the spaces
            spacingTheBoard(size-bombs);
        }
```
```java
public static void spacingTheBoard(int numberSpaces) {
        Random random = new Random();
        while (numberSpaces > 0) {
            Integer trial = new Integer(random.nextInt(myBoard.size()));

            if (myBoard.get(trial) instanceof Mine) {
                myBoard.put(trial, new Space());
                numberSpaces--;
            }
        }
    }
```

**Strategy**: Understand well what the code does. Use conditionals breakpoints.


### Exercise 3
In this case, this code looks really simple. When the "d" reaches the value 1.0, the program should end, but it never does.

#### Why does not appear a message indicating that "d is 1"?
That's because double variables are stored in binary (2 base) while humans work with base 10. Making it impossible to represent certain fractions. 

>Base 10 has no way to exactly represent the fraction 1/3. You can approximate it as 0.3333333, but eventually, you reach the >limit of how many digits you can store, so you have to stop. In base 2, it happens that one of the fractions you can’t >accurately represent is the decimal value 1/10

For more information click [here](https://www.dummies.com/programming/java/weird-things-java-math/)

#### How will you fix it?
After some research, I found that the best way is to use _BigDecimal_ which is a class that offers greater precision in these cases. 

```java
 public static void main(String [] args) {
            BigDecimal d = new BigDecimal(0.0);
            BigDecimal max = new BigDecimal(1.0);

            while (d.equals(max)) {
                BigDecimal add = new BigDecimal(0.1);
                d = d.add(add);
            }

            System.out.println("d is 1");
        }
```
