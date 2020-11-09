exception NotImplemented
exception Error of string

(* Abstract syntax trees produced by parse *)
type exp =
  | Sum of exp * exp
  | Minus of exp * exp
  | Prod of exp * exp
  | Div of exp * exp
  | Int of int

(* Tokens produced by the lexer *)
type token = SEMICOLON | PLUS | SUB | TIMES | DIV | LPAREN | RPAREN | INT of int


(* -------------------------------------------------------------------------- *)
(* Everything below this line is lexer/evaluator code. You do not need to     *)
(* read it to do the homework (but it would be a good idea to understand it). *)
(* -------------------------------------------------------------------------- *)
let rec tabulate f n =
  let rec tab n acc =
    if n < 0 then acc
    else tab (n - 1) ((f n) :: acc)
  in
  tab (n - 1) []

let string_explode s =
  tabulate (String.get s) (String.length s)

let string_implode l =
  List.fold_right (fun c s -> Char.escaped c ^ s) l ""

let is_digit c =
  c = '1' || c = '2' || c = '3' || c = '4'
  || c = '5' || c = '6' || c = '7' || c = '8'
  || c = '9' || c = '0'

let rec lexChars = function
  | [] -> []
  | ';' :: l -> SEMICOLON :: lexChars l
  | '+' :: l -> PLUS :: lexChars l
  | '-' :: l -> SUB :: lexChars l
  | '*' :: l -> TIMES :: lexChars l
  | '/' :: l -> DIV :: lexChars l
  | '(' :: l -> LPAREN :: lexChars l
  | ')' :: l -> RPAREN :: lexChars l
  | ' ' :: l -> lexChars l
  | d :: l -> let (n, l') = lexDigit (d :: l) in INT n :: lexChars l'

and lexDigit l =
  let rec split = function
    | (d :: rest) ->
        if is_digit d then
          let (digit, rest') = split rest in (d :: digit , rest')
        else
          ([], d :: rest)
    | [] -> ([], [])
  in
  let (digit_list, rest) = split l in
  let digit_string = string_implode digit_list in
  try
    let n = int_of_string digit_string in (n, rest)
  with
    Failure _ -> raise (Error ("Invalid number " ^ digit_string))

let lex s = lexChars (string_explode s)

(* Evaluating an abstract syntax tree *)
let rec eval' e = match e with
  | Sum (e1, e2) -> eval' e1 + eval' e2
  | Minus (e1, e2) -> eval' e1 - eval' e2
  | Prod (e1, e2) -> eval' e1 * eval' e2
  | Div (e1, e2) -> eval' e1 / eval' e2
  | Int n -> n


(* --------Hamming numbers------------ *)


type 'a susp = Susp of (unit -> 'a) ;;
let force (Susp s) = s () ;;
let delay f = Susp f ;;

type 'a str = {hd: 'a  ; tl : ('a str) susp} 

let rec map f s = 
  { hd = f (s.hd) ; 
    tl = Susp (fun () -> map f (force s.tl))
  }

let times k = map (fun x -> x * k) ;;

(*------------------ Q1------------------ *)
let rec parseExp (toklist: token list) (sc: (token list -> exp -> 'a)) : 'a =
  parseSExp
    toklist
    (fun toklist' exp -> match toklist' with
       | SEMICOLON :: toklist'' -> sc toklist'' exp
       | _ -> raise (Error "Expected a single semicolon"))

and parseSExp (toklist: token list) (sc: (token list -> exp -> 'a)) : 'a =
  raise NotImplemented

and parsePExp (toklist: token list) (sc: (token list -> exp -> 'a)) : 'a =
  raise NotImplemented

and parseAtom (toklist: token list) (sc: (token list -> exp -> 'a)) : 'a =
  raise NotImplemented

(* parse : string -> exp *)
let parse string =
  parseExp
    (lex string)
    (fun s e -> match s with
       | [] -> e
       | _ -> raise (Error "Incomplete expression"))

(* eval : string -> int *)
let eval e = eval' (parse e)

(* ---------- Hamming Numbers ----------- *)

let rec merge s1 s2 = 
  ()

let rec hamming_series = 
  ()

  )
let rec merge s1 s2 = (*raise TODO*)
  if s1.hd < s2.hd then
    { hd = s1.hd;
      tl = Susp (fun () -> merge (force s1.tl) s2)
    }
  else if s1.hd > s2.hd then
    { hd = s2.hd;
      tl = Susp (fun () -> merge s1 (force s2.tl))
    }
  else
    { hd = s1.hd;
      tl = Susp (fun () -> merge (force s1.tl) (force s2.tl))
    }
let rec hamming_series = 
  { hd = 1; 
    tl = Susp (
        fun () -> (
            merge
              (
                merge 
                  (map ( fun x -> 2 * x ) hamming_series)
                  (map ( fun x -> 3 * x ) hamming_series)            
              )
        
              (map ( fun x -> 5 * x ) hamming_series)
          )
      )
  }