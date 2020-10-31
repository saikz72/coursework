(*Data types and pattern matching 1*)

let isBigger (a, b) = match (a, b) with
  | (1,5) -> true 
  | (a,b) -> a = b
             
             
type suit = Clubs | Spades | Hearts | Diamonds 
            
type rank = Two | Three | Four | Five | Six | Seven | Eight | Nine | Ten | 
            Jack | Queen | King | Ace
            
type card = rank * suit 
            
(** could have name Hand as StackOfCard --> *)
type hand = Empty | Hand of card * hand
                            
let hand0:hand = Empty
let hand1:hand = Hand ((Ace, Hearts), Empty)
let _hand = Hand((Two, Spades), Empty) 
let hand2: hand = Hand((Ace, Diamonds), _hand)

(* Hearts hand1 -->> hand1 
  Sapdes hand5(in notes) -->  Hand((Ace, Spades), Hand((Queen, Spades), Empty))*
   Diamonds hand5 (in notes) --> Hand ((Ten, Diamonds), Empty)*)    
let rec extract (s:suit) (h:hand) =  match h with 
  | Empty -> Empty
  | Hand (( _, s') as c, h') -> 
      if s = s' then 
        Hand (c, extract s h') 
      else
        extract s h' 
  
let rec find ((r:rank), (h:hand))= match h with 
  | Empty -> None
  | Hand ((r', s'), h') -> 
      if r = r' then
        Some s'
      else
        find (r, h')