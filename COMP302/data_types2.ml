(**List**)
(*type + list*)
let f10 : float list = 8.6 :: 5.4 :: []
let intList : int list = 2 :: (5 :: [])
                                   
                           
(**OR it could also be written like so**)

let f110 = [8.6; 5.4]
let intList2 = 2 :: 5 :: [] (**--- same as int list = [1;2;3] *)
let l1 = "x"::"y"::"z"::[]
let l2 = ["a"; "b"; "c"]::[]
let l3 = "m"::"n"::"p"::[]
(* we can store anything*)

let l1l3 = l1::l3::[]

  
(* append method to appen two list together*)
(* does not update parameters*)

let rec append list1 list2 = match list1 with 
  | [] -> list2
  | x:: t -> x :: append t list2 
               
(** l1 = [1;2;3] l2 = [4;5] --> 1 :: 2:: 3:: [4 ;5]*)


(**to get the tail of a function*)

let tail l = match l with
  | [] -> []
  | h :: t -> t
  
(*to get the head *)
let head (h::t) = h
(* Note : this function will cause a runtime error if list is empty*)


(** another version of append using the above define procedures *)

let rec append2 (l1,l2) = 
  if l1 = [] then 
    l2
  else
    head(l1)::(append2 (tail(l1), l2)) 
 

(** Function to reverse a list **)

(** Quadratic runtime implmentation(O(n^2))**)
let rec reverse l = match l with
  | [] -> [] 
  | h :: t -> reverse (t) @ [h]

(** Linear runtime implementation (O(n))*)

let reverse2 l = 
  let rec helper l acc = match l with
    | [] -> acc
    | h :: t -> helper t (h::acc)
  in
  helper l []
    






