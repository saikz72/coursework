let rec sum f (a,b) = 
  if a > b then 0 else (f a) + sum f (a + 1, b)
                                     
let rec sum2 f (a, b) inc = 
		if a > b then 0 else (f a) + sum2 f (inc(a) , b) inc
                       
(** summing up odd (resp. even) numbers betwwen a and b*)

let rec sumOdd (a, b) = 
  if (a mod 2) = 1 then 
    sum (fun x -> x) (a, b) 
  else
    sum (fun x -> x) (a + 1, b)
                     
let rec sumOddWithInc (a, b) = 
  if (a mod 2) = 1 then 
    sum2 (fun x -> x) (a, b) (fun x -> x + 2)
  else
    sum2 (fun x -> x) (a + 1, b) (fun x -> x + 2)