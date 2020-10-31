(*INDUCTION :)*)
(** proves on basic structural induction **)
(* returns the lenght of a list *)
let rec length l = match l with
| [] -> 0
| _::tail -> 1 + length l
