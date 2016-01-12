# Schelling [![Build Status](https://travis-ci.org/nicholasyager/schelling.svg?branch=master)](https://travis-ci.org/nicholasyager/schelling)
A Java-based model for n-race Schelling segregation.

Thomas Schelling is an American economist who made breakthroughs in the
application of game theory to economics, politics,  and sociology. One
interesting creation was his the Schelling segregation model: a way of showing
how small-scale racial prejudices in one's neighbors can result in segregation.

!(3-race example simulation)[https://nicholasyager.com/2016-01-01/example.gif|alt=example]

While the Schelling segregation model is wildly simplistic in nature, it
remains useful as a means of examining how even minuet social tensions may
result in a larger image of inequality. This can also have applications in
meme theory, as well as some predictive power in finding ways to reverse
long-standing segregation patterns.

## Usage
```{bash}
usage: schelling [-h] [-he <arg>] [-o <arg>] [-r <arg>] [-s <arg>] [-t
       <arg>] [-wi <arg>]
A Java-based Schelling segregation simulator.

 -h,--help
 -he,--height <arg>     The height of the world.
 -o,--output <arg>      The output directory for the simulation.
 -r,--races <arg>       The number of races to simulate.
 -s,--steps <arg>       The number of steps to simulate.
 -t,--threshold <arg>   The minority threshold.
 -wi,--width <arg>      The width of the world.

Please report issues to yager@nicholasyager.com
```

## References
Schelling, Thomas C. 1971. "Dynamic Models of Segregation." Journal of
Mathematical Sociology 1:143-186. 
