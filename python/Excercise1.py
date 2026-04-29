# CMP 269: Programming Methods III
# Python Introduction Exercises 

def exercise_1_basics():
   """
   Goal: Practice basic syntax and string formatting.
   Task: Create variables for a course name and a number of students.
   Print a sentence using an f-string.
   """
   # TODO: Print "The course [course] has [students] students."

   course = "Programing 3"
   NumOfStu = 42

   print(f"The course {course} has {NumOfStu} students.")



def exercise_2_collections():
   """
   Goal: Manipulate lists and dictionaries.
   Task: 
   1. Create a list of 5 colors.
   2. Add a 6th color to the end.
   3. Create a dictionary with keys 'name' and 'gpa'.
   """

   colors = ["red", "blue", "yellow", "pink", "black"]

   print("colors:", colors)

   colors.append("white")
   print("colors after append:", colors)

   mydic = {"name": "gpa"}

   print("dictionaries:", mydic)



def exercise_3_logic():
   """
   Goal: Use loops and conditionals.
   Task: Iterate through a list of numbers. 
   If a number is even, add it to a new list called 'evens'.
   """
   nums = [1,2,3,4,5,7,8,10,11,12,13]
   evens = []

   for num in nums:
      if num % 2 == 0:
         evens.append(num)

   print("evens:", evens)


if __name__ == "__main__":
   print("--- Exercise 1 ---")
   exercise_1_basics()
   print("\n--- Exercise 2 ---")
   exercise_2_collections()
   print("\n--- Exercise 3 ---")
   exercise_3_logic()

