type 'a tree = Empty | Node of 'a * 'a tree * 'a tree 

let tree0 = Empty
let tree1 = Node ((1, "saikou"), Empty, Empty)
let tree2 = Node ((2, "modou"), tree1, Empty)
let tree3 = Node ((3, "rohey"), tree1, tree2)
                                 

(**inserting into a binary tree*)
(* the nodes of the tree consists of key-val pair of int - string*)

let rec insert ((x, d) as e) t = match t with
  | Empty -> Node((x, d), Empty, Empty)
  | Node ((x', d'), l, r) -> 
      if x = x' then
        Node (e, l, r)
      else(
        if x < x' then
          Node ((x', d'), insert e l, r)
        else
          Node ((x', d'), l, insert e r)
      )
           
  
(**implementing a lookup function*)
let rec lookup k t = match t with
  | Empty -> None
  | Node((x', d'), l, r) -> 
      if k = x'  then
        Some(d')
      else (
        if k < x' then
          lookup k l
        else
          lookup k r
      ) 