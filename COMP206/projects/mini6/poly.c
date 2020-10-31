/**
 * this file contains the template for creating a linklist of polynomials. It contains three main functions. 
 *****************************************************************************************************************
 *Author			Dept				Date				Note
 *****************************************************************************************************************
 *Saikou			Soft. Eng			April 14, 2020			initial Version
 *
**/

#include <stdio.h>
#include <stdlib.h>
#include "utils.h"


//function declarations
int addPolyTerm(int, int);
int evaluatePolynomial(int);
void displayPolynomial();

struct PolyTerm{
	int coeff;
	int expo;
	struct PolyTerm *next;
};
//head pointer
struct PolyTerm *head = NULL;

//assimilates a new term into the polynomial
int addPolyTerm(int c, int e){
	struct PolyTerm *newNode = (struct PolyTerm *)malloc(sizeof(struct PolyTerm *));
	struct PolyTerm *temp = head;  //stores the head and use to traverse the list
	if(newNode == NULL) {
		return -1;
	}
	newNode->coeff = c;
	newNode->expo = e;
	if(temp == NULL){
		newNode->next = NULL;
		head = newNode;
		return 0;
	}else{
		if(newNode->expo == temp->expo){
			temp->coeff += newNode->coeff;
			head = temp;
		}else if(newNode->expo < temp->expo){
			newNode->next = temp;
			head = newNode;
		}else{
			while(temp->next != NULL && temp->next->expo < newNode->expo){
				temp = temp->next;
			}
			 if(temp->next == NULL){
                                temp->next =  newNode;
                        }
			else if(temp->next->expo == newNode->expo){
				temp->next->coeff += newNode->coeff;
			}else{
				newNode->next = temp->next;
				temp->next = newNode;
			}
		}
	}
	return 0;
}

//displays the polynomial expression
void displayPolynomial(){
	printf("%dx%d", head->coeff,head->expo);
	struct PolyTerm *temp = head->next;
	while(temp != NULL){
		if(temp->coeff > 0){
			printf("+");
		}
		printf("%dx%d", temp->coeff, temp->expo);
		temp = temp->next;
	}
	printf("\n");
}

//returns an integer obtained by evaluating the polynomial
int evaluatePolynomial(int num){
	struct PolyTerm *temp = head;
	int result = 0;
	while(temp != NULL){
		result += temp->coeff * powi(num, temp->expo);
		temp = temp->next;
	}
	return result;
}
