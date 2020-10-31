(* TODO: Write some tests for tabulate. *)
(* Note: we've added a type annotation here so that the compiler can help
   you write tests of the correct form. *)
let tabulate_tests: (((int -> int) * int) * int list) list = [
  (* Remember: your test cases should have this form:
((f, n), output)
  The following test case asserts that:
  tabulate (fun x -> x) (-1)
    should have the output []
  *)
  (((fun x -> x), -1), []);
  (((fun x -> x + 2), 2), [2; 3; 4]);
  (((fun x -> x * x), 2), [0; 1; 4]);
  (((fun x -> 1), 0), [1]);
]

(* TODO: Implement dist_table: (int * int) -> int -> float list *)
let dist_table ((marblesTotal, marblesDrawn): (int * int)) (x: int) : float list =
  let f n = dist_black n x (marblesTotal, marblesDrawn) in
  tabulate f marblesTotal 

(* TODO: Write some test cases for is_empty. *)
let is_empty_tests: (float list list * bool) list = [
  ([[]], true);
  ([[1.2]; []], false);
  (([[]; []]), true);
]

(* TODO: Implement is_empty: 'a list list -> bool *)
let is_empty (matrix: 'a list list) : bool = 
  let p l = match l with
    | [] -> true
    | _ -> false 
  in
  List.for_all p matrix

(* TODO: Implement dist_matrix: int * int -> int list -> float list list *)
let dist_matrix ((total, drawn): int * int) (resultList: int list) : float list list =
  let p = dist_table (total, drawn) in
  List.map p resultList

(* TODO: Implement combined_dist_table: float list list -> float list *)
let rec combined_dist_table (matrix: float list list) = 
  let f = ( *. ) in
  let p x y = match y with 
    | [] -> x
    | _ -> List.map2 f x y
  in
  List.fold_right p matrix []

(* Once you have implemented all the above functions, you can
   use this function to compute the maximum likelihood.
   You can try it on the given example by running:
     max_likelihood (6, 3) [2; 0; 1]
*)
let max_likelihood (total, drawn) resultList =
  max_in_list
    (combined_dist_table
       (dist_matrix (total, drawn) resultList))


(* TODO: Implement all: (ingredients list -> bool) -> cake -> bool *)
let rec all (p: (ingredients list -> bool)) (c: cake) : bool = match c with
  | Slice l -> p l 
  | Cake (c1, c2) -> all p c1 && all p c2 
 

(* TODO: Write some test cases for is_chocolate_cake. *)
let is_chocolate_cake_tests = [
  ((Slice [Chocolate; Flour]), true);
  ((Slice [Flour]), false);
  ((Cake (Slice [Flour; Vanilla], Slice [Chocolate])), false);
]

(* TODO: Implement is_chocolate_cake: cake -> bool *)
let is_chocolate_cake (c: cake) : bool =
  let rec p l = match l with
    | [] -> false
    | x::xs -> (x = Chocolate) || (p xs) 
  in
  all p c

(* TODO: Implement map: (ingredients list -> ingredients list) -> cake -> cake *)
let rec map (p: (ingredients list -> ingredients list)) (c: cake) = match c with
  | Slice l -> Slice (p l)
  | Cake (c1, c2) -> Cake ( map p c1, map p c2)

(* TODO: Write some test cases for add_ingredient. *)
let add_ingredient_tests = [
  ((Chocolate, Slice []), Slice [Chocolate]);
  ((Almonds, Cake(Slice [Almonds], Slice [])), Cake(Slice [Almonds], Slice [Almonds]));
]

(* TODO: Implement add_ingredient: ingredients -> cake -> cake *)
let add_ingredient (x: ingredients) (c: cake) : cake = 
  let rec p l = match l with
    | [] -> x::l
    | y::xy -> if x = y then l else y::p xy
  in
  map p c


(* TODO: Implement fold_cake: (ingredients list -> 'a -> 'a) -> 'a -> cake -> 'a  *)
let rec fold_cake (f: (ingredients list -> 'a -> 'a)) (base: 'a) (c: cake) : 'a =
  match c with
  | Slice l -> f l base
  | Cake (c1, c2) ->  fold_cake f (fold_cake f base c1) c2


(* TODO: Implement get_all_ingredients: cake -> ingredients list *)
let get_all_ingredients (c: cake) : ingredients list = 
  let rec f l a = match l with
    | [] -> a
    | [x] -> if (List.mem x a ) then a else x::a
    | x::xs -> if (List.mem x a) then f xs a else f xs (x::a)
  in 
  fold_cake f [] c
  
  
  
  
  
  
  
  
  