* **version:** 0.8.0
* **status:** in development - do not use this yet
* **comments:** We are in the process of pulling this library out of a larger code base
  for release. Documentation and testing are incomplete, and while we are now using this
  library, we don't think it is ready for prime-time use.

# a05annexNavX
A wrapper for the First FRC Java library for the
[NavX or NavX2 from Kauai Labs](https://www.andymark.com/products/navx2-mxp-robotics-navigation-sensor)
that lets us better control our robot. The primary issue we had with the NavX was
the -180&deg; to +180&deg; boundary. This was especially problematic for PID loops trying
to maintain robot heading near this boundary. Our solution is to eliminate the boundary so
that instead of going from -175&deg; to +175&deg;, the heading goes from -175&deg; to -185&deg;;
and instead for going from +175&deg; to -175&deg;, the heading goes from +175&deg; to +185&deg;.

As a result, the heading value is continuous where the number of revolutions the robot has made
since the start of the start of the match is encoded into the heading. Subsequently, the heading
range reported here is from -&infin; to +&infin;

The issues that we address with this wrapper are:
* The -180&deg; to +180&deg; boundary;
* An initial non-0&deg; orientation;
* Roborio mounting and/or NavX mounting orientation;
* Returning the closest down-field or up-field heading.

We first used the NavX in the 2018, it worked great; but
we needed to do a bit of work to really make it useful. We copied that code and tweaked it
for 2019 and 2020. For 2021 we decided to package it, so we could just include it and continue to
grow the functionality as required.

## Including a05annexUNavx in your build.gradle

We wanted to package `a05annexNavX` so it was easy for us to use in future years, and
easy for you to use in your FRC projects. We found this was not as easy as we had
hoped as publishing artifacts to Maven repositories is non-trivial, but we now have
it all working.

There are several paths for inclusion.

### The Best Method

Simply add it to the dependencies section of your `gradle.build` file as:
```
dependencies {
    compile 'org.a05annex:a05annexNavX:0.8.0'
     .
     .
     .
}
```

Also add a dependency for `testCompile` if you need it for testing.

## a05annexNavX Details

This section describes how you really use this NavX library in your robot code.

### Creating and Initializing a `NavX`

There is a single instance of the `NavX` class which gives the caller the navigation information. In
an ideal world a configuration method is called to describe whether the NavX is one or more of:
* Roborio mounted [NavX](https://www.andymark.com/products/navx-mxp-robotics-navigation-sensor) board
  recently replaced by the [NavX2](https://www.andymark.com/products/navx2-mxp-robotics-navigation-sensor);
* USB connected NavX micro, recently replaced by the
  [NavX2-micro](https://www.andymark.com/products/navx2-micro-navigation-sensor);
* a mocked version for testing.

We have used both the roborio mounted version, and the micro version, and have found that if there
are any problems with the connection, the robot hangs on NavX initialization, and the robot is left
dead for the match. We wanted to address this.

#### Hang on Initialization


    

