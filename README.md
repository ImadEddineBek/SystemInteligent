# System Intelligent
in this project we will discus most of the famous search and basic AI algorithms in 4 different exercises  
![project](pictures/pic1.png)
#(Un)Informed Search
this is a maze search algorithms that takes finds (a/the) path from start to end

the heuristic I used is the eucledian search 

the most basic search example uses :
```
 public ArrayList<Node<A>> solve(T problem){
        LinkedList<Node<A>> fringe = new LinkedList<>();
        fringe.add(problem.getInitialState());
        Set<Node<A>> set = new HashSet<>();
        while (true){
            if (fringe.isEmpty())return new ArrayList<>();
            Node<A> node = fringe.pop();
            set.add(node);
            if (problem.goalTest(node)){
                ArrayDeque<Node<A>> temp = new ArrayDeque<>();
                while (node!=null){
                    temp.addFirst(node);
                    node= node.getParentNode();
                }
                ArrayList<Node<A>> arrayList = new ArrayList<>(temp);
                return arrayList;
            }else {
                ArrayList<? extends Node<A>> c = problem.successorFunction(node);
                for (Node<A> aNode : c) {
                    if (set.add(aNode))
                        fringe.add(aNode);
                }
            }
        }
    }
 ```
which is pretty much the same as the one presented in the program
#Thief
this is made using simulated anealing 

the choice of the intial temrpreture was made after trying multiple values and so was the decay
#Function Maximisation
1. I chaneged the function from a simple one into a more complicated one for presentation purposes 
2. this is made using genetic algorithms.
3. the individual is an integer and it already has the shape of a cromosome (array) 
4. the crossover is made by a random splitor and creates two children and the best one is sent back
5. the choice of who lives for the next is made by thhe fitness function 
	1. the population is represnted by a sorted array 
	2. the best individuals have higher chances of staying for the next iteration
#Map Coloring
it is made using csp and the purpose is ti find an assigment of colors for the cities so we wouldn't have two neighboors with the same color
