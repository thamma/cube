# thamma / cube

> A Rubiks Cube implementation in Java focusing on the technical aspects of the cube. Thus more theory and math is available allowing more analytics to be done.

## Table of contents

* [Implementation](#implementation)
  * [The cube model](#the-cube-model)
  * [Turns](#turns)
  * [Algorithms & Interpreter](#algorithms--interpreter)
    * [Grammar](#grammar)
    * [Semantics](#semantics)
    * [Syntactic Sugar](#syntactic-sugar)
* [Examples](#examples)
  * [Usage](#usage)
  * [Tools](#tools)

## Implementation

Below I'm going to briefly discuss the implementation.

### The cube model 

The cube itself is represented as an int[26] array. As one can guess these 26 ints decode the pieces of the cube. However, the decoding requires some explanation. Lets consider this example of a solved cube:
```
{5610, 510, 3510, 610, 10, 310, 6210, 210, 2310, 650, 60, 620, 20, 320, 30, 350, 50, 2640, 240, 3240, 640, 40, 340, 6540, 540, 5340}
```
Each number contains up to four bits of information. Reading the single digits from right to left they mean 'piece rotation', 'first color', 'second color', 'third color'. The rotation can be 0 (for centers), 0-1 (for edges) or 0-2 (for corners). The colors are enumerated by their relative positions: NaN(0), Up(1), Front(2), Right(3), Down(4), Back(5), Left(6). As you can imagine, NaN is used for the second and third center color as well as for the third edge color.

I'm will further elaborate the rotatoins in the next section, Turns.

### Turns

Turns are either natively implemented or composed by other turns. All of the elementary turn, which are
```
U, F, R, D, B, L, M, E, S
```
as well as their inverses (for performances sake) are natively implemented. A native implementation of a Turn enum looks somewhat like this:
```
FRONT(2, new Sticker[]{UFL, UF, URF, FR, DFR, DF, DLF, FL}, new int[]{-1, 1, 1, 1, -1, 1, 1, 1})
```
The first argument resembles the offset of the second argument, the pieces which are suppsed to be cycled. The third argument is the rotation value added to a piece upon turning. A composition, for example, might look like this:
```
X(LEFT_PRIME, MIDDLE_PRIME, RIGHT)
```
Luckily, the edges' orientation works in a similar way as the 'bad' edges from the ZZ method. An edges orientation is equal to 1 iff the edge is considered a bad edge. The corners orientation however was quite challenging and I won't bother explaing it here. If there are any concerns or questions about this topic, feel free to send me an email.

### Algorithms & Interpreter
An algorithm is basically a list of turns with added fancyness. Algorithms can not only be constucted from several turns, but — more intuitively — from strings using the well-known Rubiks cube notation. In addition to the basic notation, this project supports advanced notation as elaborated below.

#### Grammar
The interpreter interpretes expressions which suffice the grammar given by this [BNF](https://en.wikipedia.org/wiki/Backus%E2%80%93Naur_Form):
```
<exp> ::= <exp> <exp>* | <pexp>
<par_exp> ::= ( <exp> ) <appendix> | <com_exp> [appendix] | <turn_exp>
<com_exp> ::= [<exp> : <exp>] | [<exp> , <exp>]
<turn_exp> ::= <turn> [appendix]

<turn> ::= U | Uw | … | M | E | S | x | y | z
<appendix> ::= ['] [number]
<number> ::= ℕ
```

#### Semantics
```
⟦AB⟧ = ⟦A⟧ * ⟦B⟧
⟦A'⟧ = A⁻¹
⟦(A)n⟧ = A * … * A                     (n times)   ∀n∈ℕ
⟦[A:B]⟧ = A B A⁻¹
⟦[A,B]⟧ = A B A⁻¹ B⁻¹
```

#### Syntactic Sugar
Since the commutator notation is very powerful, it deserves some love considering the accumulation of brackets. Thus this syntactic sugar is justified:
```
[A °¹ B °² ... Z] = [A °¹ [ B °² [ ... Z] ] ]
```
With °i being either ':' or ','. Also, you are able to use these more intuitive definitions for cube rotations:
```
[R] := x
[U] := y
[F] := z
(and so on)
```

### Examples
[show use cases]

#### Usage
[show usage of java implementation]

#### Tools
[introduce minor applications]
