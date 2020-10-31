(*

1. A slice (type a') is a cake of type 'a cake
2. If c1,c2 are cakes, then Cake(c1, c2) is a cake
3. Nothing else is a cake
   
  *)
  
type ingredients = Cherries | Chocolate | Oreo | Strawberries | Blackbeans | Flour | Eggs
                   
type 'a cake = Slice of 'a | Cake of 'a cake * 'a cake
                                       
                                       
let choc_cake = Cake (Slice Chocolate, Cake (Slice Chocolate, Slice Chocolate))
    
let mixed_cake = Cake (Slice [Eggs ; Flour; Chocolate],
                       Slice [Blackbeans ; Chocolate; Oreo])
  

(** Function --> How many slices are in the cake?? *)

let rec countNumCake c = match c with
  | Slice _ -> 1
  | Cake (c1, c2) -> countNumCake c1 + countNumCake c2
                       
(* Function --> get all the ingredients in a cake*)

(* A --> get all : ingredients cake -> ingredients list 
   B--> get all : 'a cake -> 'a list
   we can say that B is more general than A
     
  C --> get_all : (ingredients list) cake -> ingredients list
   
  is the type C an instance of B
*)
let rec getAllIngredients c = match c with
  | Slice i -> [i]
  | Cake (c1, c2) -> getAllIngredients c1 @ getAllIngredients c2
                       
  

(** Mergesort 
    
  split: 'a list -> ('a list * 'a list)
    
  Example: given a list [1;2;3;4;5] 
           split [ 1;2;3;4;5] returns ([1;2;3], [2;4])*)

let rec split l = match l with
  | [] -> ([],[])
  | [x] -> ([x], [])
  | x ::(( y::tail) as xs) ->  (* notice that xs is not needed but to name the y + tail*) 
      let (l1, l2) = split tail in 
      (x::l1 , y::l2)
      
let rec split2 l = match l with
  | [] -> ([],[])
  | x::xs -> (match xs with
      | [] -> ([x], [])
      | y::tail -> 
          let (l1, l2) = split2 tail in
          (x::l1, y::l2)) 
    
  
(** zip: 'a list * 'a list -> 'a list*
    Example: zip ([1;3;5], [2;4]) --> [1;2;3;4;5]*)

let rec zip (l1, l2) = match l1, l2 with 
  | [], l | l, [] -> l  (**its called an or pattern*)
  | x::xs, y::ys -> x::y::zip(xs, ys)
  
  
  
(*merge (l, l') = lst *)






















  ls