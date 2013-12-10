package com.gmap;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;



public class SikuliAPITestofTest {

	public SikuliAPITestofTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Screen s = new Screen();
        try{
                s.click("imgs/spotlight.png", 0);
                s.wait("imgs/spotlight-input.png");
                s.type(null, "hello world\n", 0);
        }
        catch(FindFailed e){
                e.printStackTrace();
        }
	}

}
