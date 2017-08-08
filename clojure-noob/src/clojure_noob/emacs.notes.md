### EMACS Notes

#### Buffers

Open a buffer: `C-x b RET`  
Save buffer as file: `C-x C-s`  
Save all and close: `C-x C-c`  

#### Frames and Windows 

`C-x o`	Switch cursor to another window. Try this now to switch between your Clojure file and the REPL.  
`C-x 1`	Delete all other windows, leaving only the current window in the frame. This doesn’t close your buffers, and it won’t cause you to lose any work.  
`C-x 2`	Split frame above and below.  
 `C-x 3`	Split frame side by side.  
 `C-x 0`	Delete current window.  

#### Clojure / Cider

 Change Mode: M-x clojure-mode RET   
 Set Namespace for Cider (to the active file): C-c M-n  
 Compile Active Clojure: C-c C  

 C-c C-d C-d - show documentation under point (cursor) - q closes buffer  
 C-c C-d C-a - search for documentation accross function names  
 C-x C-e - evaluate expression preceeding point  
 C-c C-k - compile current buffer  

#### Editting Text:

C-k - kill all text from cursor (point) to end of line  
C-/ - Undo last operation  
C-a C-SPACE C-n M-w C-y Duplicate a whole line  
M-d kill to end of word  
C-d kill to end of line  
C-<space> (Select Block) M-; to toggle comment on or off  

[https://github.com/georgek/paredit-cheatsheet/blob/master/paredit-cheatsheet.pdf](Paredit Cheats)  

#### Slurping and Barfing

C - → : pulls the next character into the current set of parenthesis (slurp)  
C - ← : pushes the last character (symbol) of the current set of parenthesis out (barf)  
