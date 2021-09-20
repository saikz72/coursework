#!/usr/bin/env python3

# ECSE211 - Design Principles and Methods
# Assignment 2 - Introduction to Python

# Assignment setup: DO NOT MODIFY
from __future__ import annotations  # required until Python 3.10+
from statistics import mean  # mean = average
from textwrap import dedent
from types import SimpleNamespace as ColorNames
from math import sqrt
COLOR = ColorNames(VIOLET="\033[95m", BLUE="\033[94m", CYAN="\033[96m", GREEN="\033[92m", YELLOW="\033[93m",
                   ORANGE="\u001b[31;1m", RED="\033[91m", ENDC="\033[0m")
color_str = lambda color, text: f"{color}{text}{COLOR.ENDC}"  # return the given text in the given color
dummy_function_template = lambda *_: _

# End assignment setup. Add additional imports here.
...


NAME = "Your name here"
STUDENT_ID = ...


###################################################################
# Part 1 - Review of basic tutorial concepts
###################################################################

# Question 1.1
# Python expression that represents the sum of 2 and 2.

q1_1 = 2 + 2


# Question 1.2

def q1_2():
    "Print and return the value of the nested sum shown in the assignment description."
    # TODO: Write your code here. Store the value of the sum in the variable "total".
    total = 0
    for i in range(21):
        for j in range(22):
            total += 2*i + j
    print(f"Q1.2 nested sum is {total}.")
    return total


# Question 1.3

def q1_3():
    "Print and return the a list of length 21 with the pattern [0, -3, 6, -9, ...]."
    result: list[int] = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21]

    for i in range(len(result)):
        if i % 2 == 0:
            result[i] *= 3
        else:
            result[i] *= -3

    print(f"Q1.3 list is {result}.")
    return result


# Question 1.4

def q1_4():
    "Print and return the average hours for the students in the format specified by the assignment description."
    ecse200_hours: dict[str, float] = {"Alpha": 3.5, "Bravo": 6.0, "Charlie": 5.5} 
    ecse202_hours: dict[str, float] = {"Alpha": 4.0, "Bravo": 3.5, "Charlie": 2.0} 
    math141_hours: dict[str, float] = {"Alpha": 5.0, "Bravo": 3.0}  # Charlie does not take this course 

    average_hours: dict[str, float] = {}
    
    alpha_hours = round((ecse200_hours["Alpha"] + ecse202_hours["Alpha"] + math141_hours["Alpha"]) / 3, 1)
    bravo_hours = round((ecse200_hours["Bravo"] + ecse202_hours["Bravo"] + math141_hours["Bravo"]) / 3, 1)
    charlie_hours = round((ecse200_hours["Charlie"] + ecse202_hours["Charlie"]) / 2, 1)

    average_hours["Alpha"] = alpha_hours
    average_hours["Bravo"] = bravo_hours
    average_hours["Charlie"] = charlie_hours

    print(f"Q1.4 student average hours are {average_hours}.")
    return average_hours

# Question 1.5

def calculate_student_avg_hours(courses: list[dict[str, float]]) -> dict[str, float]:
    "Calculate and return student average hours for the given courses."
    average_hours: dict[str, float] = {}
    students: dict[str, list[float]] = {}

    for hours_spent_by_student in range(len(courses)):
        for student, hour in hours_spent_by_student.items():
            if student in students:
                students[student] = students[student].append(hour)      # error here!!!!
            else:
                students[student] = hour
    
    for student, hours in students.items():
        total = sum(hours)
        average_hours[student] = round(total / len(hours), 2)

    return average_hours


###################################################################
# Part 2 – Find the bugs
###################################################################

# Edit the code that is already there to fix the bugs.

# Question 2.1

def q2_1():
    "Print and return the sum of numbers from 0 to 100, inclusive."
    sum = 0
    for i in range(101):
        sum += i
    print(f"Q2.1 sum is {sum}")
    return sum


# Question 2.2

def q2_2():
    "Print and return welcome_message after replacing all references of '2020' with '2021'."
    welcome_message: str = "Welcome, Class of 2020! Your graduation will take place on October 31, 2020."
    welcome_message = welcome_message.replace("2020", "2021")
    print(f"Q2.2: {welcome_message}")
    return welcome_message


# Question 2.3

def find_three_smallest_even(nums: list[int]):
    """
    Return the three smallest even numbers (if any) in the input list, allowing duplicates, eg,

    [1, 2] -> [2]
    [1, 2, 2, 3, 4, 5, 6, 7, 8] -> [2, 2, 4]
    """
    OUTPUT_SIZE = 3
    INF = float("inf")
    smallest_numbers: list[int] = []
    used_indices: list[int] = []

    for _ in range(OUTPUT_SIZE):
        smallest_even = INF
        idx = 0
        for i in range(len(nums)):
            if i not in used_indices and nums[i] < smallest_even and nums[i] % 2 == 0:
                smallest_even = nums[i]
                idx = i
        if smallest_even < INF:
            smallest_numbers.append(smallest_even)
            used_indices.append(idx)

    return smallest_numbers

# Question 2.4
def q2_4():
    "Print and return a rainbow of seven color names."
    colors: dict[str, list[float]] = {
        "violet": COLOR.VIOLET,
        "indigo": COLOR.BLUE,
        "blue": COLOR.CYAN,
        "green": COLOR.GREEN,
        "yellow": COLOR.YELLOW,
    }

    # Add missing colors to make a rainbow
    colors.update({
        "orange": COLOR.ORANGE,
        "red": COLOR.RED
    })

    output = []
    for color_name, color in colors.values():
        output += f"{color_str(color, color_name)} "
    print(f"Q2.4 rainbow: {output}")
    return output


# ###################################################################
# # Part 3 - Code documentation
# ###################################################################

# TODO Document the code below.

# assume these functions are defined and behave according to their naming
get_sensor_readings = move_straight = move_left = move_right = log_to_file = dummy_function_template

def aw():
    """
    ...
    """
    while True:
        r = get_sensor_readings()
        a = mean(r)
        if a < 20:
            move_left()
        elif a > 80:
            move_right()
        else:
            move_straight()


def g(n):
    """
    ...
    """
    if 0 <= n < 50:
        return "F"
    elif 50 <= n < 55:
        return "D"
    elif 55 <= n < 65:
        return "C"
    elif 65 <= n < 80:
        return "B"
    elif 80 <= n:
        return "A"


def r(s, n):
    """
    ...
    """
    log_to_file(f"{s.name},{n},{g(n)}")


def alls():
    """
    ...
    """
    s = {
        "Alpha": 89,
        "Bravo": 63,
        "Charlie": 79.5,
    }
    for k, v in s.items():
        r(k, v)


# ###################################################################
# # Part 4 - Translation from Java
# ###################################################################

class Point:
    EPSILON = 0.003

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def distance_to(self, other: Point):      #use type alias instead :: probably wrong!!
        dx = other.x - self.x
        dy = other.y - self.y
        return sqrt((dx * dx) + (dy * dy)

    def make_points_from_string(self, s : str):
        result = []
        if s == None or ')' not in s:
            return result
        s = s.replace("\\s+", "").replace("\\(", "").replace("\\)", "\\)")
        
        for fragment in s.split("\\"):
            xy = fragment.split(",")
            result.append(Point(float(xy[0]), float(xy[1])))
        
        return result

    def __eq__(self, o):
        if not isinstance(o, Point):
            return false
        Point other = Point(o)
        return abs(self.x - other.x) < EPSILON and abs(y - other.y) < EPSILON

    def __str__(self):
        return "(" + round(self.x, 2) + ", " + round(self.y, 2) ")"
 

# End of graded part of the assignment

if __name__ == "__main__":
    """
    Main entry point. After all the code is defined above, the following statements are run to test it.
    Feel free to modify the code below to test your code, it is not graded.
    """
    print(dedent(f"""\
        Make sure that your student name and ID are correctly set:
        Name: {NAME}
        Student ID: {STUDENT_ID}"""))

    # Questions 1.1 - 1.4
    print(f"Q1.1: {q1_1}")
    q1_2()
    q1_3()
    q1_4()
    
    # Question 1.5
    courses = [
        ecse200_hours := {"Alpha": 3.5, "Bravo": 2.5, "Charlie": 4},
        ...
    ]
    print(f"Q1.5 student average hours are {calculate_student_avg_hours(courses)}")

    # Questions 2.1 - 2.2
    q2_1()
    q2_2()

    # Question 2.3
    print("Q2.3:")
    for test_list in [[], [1, 3, 5], [1, 2], [1, 2, 2, 3, 4, 5, 6, 7, 8]]:
        print(f"find_three_smallest_even({test_list}) = {find_three_smallest_even(test_list)}")

    # Question 2.4
    try:
        q2_4()
    except ValueError as e:
        print(color_str(COLOR.RED, "There is still at least one bug in Q2.4. Fix it to print the rainbow!"))

    # Question 3 is about documentation only, so no need to test your answers. Make sure you fill all the ...

    # Question 4
    # Uncomment the following lines after completing the question to test your Point class
    # print("Q4:")
    # points = Point.make_points_from_string("(1,1.25), (2,5),(-3, 3)")
    # for p in points:
    #     print(p)
    # print(f"The distance between {(p1 := points[0])} and {(p2 := points[1])} is {p1.distance_to(p2)}.")
