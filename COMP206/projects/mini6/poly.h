struct PolyTerm{
	int coeff;
	int expo;
	struct PolyTerm *next;
};

extern struct PolyTerm head;

int addPolyTerm(int c, int e);
int evaluatePolynomial(int num);
void displayPolynomial();
