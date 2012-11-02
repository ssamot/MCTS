/*
 *
 * *** BEGIN LICENSE
 *  Copyright (C) 2012 Spyridon Samothrakis spyridon.samothrakis@gmail.com
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License version 3, as published
 *  by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful, but
 *  WITHOUT ANY WARRANTY; without even the implied warranties of
 *  MERCHANTABILITY, SATISFACTORY QUALITY, or FITNESS FOR A PARTICULAR
 *  PURPOSE.  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program.  If not, see <http://www.gnu.org/licenses/>.
 * *** END LICENSE
 *
 */

package ssamot.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameConfig {
	private  static String runTimeDirectory =  "./runtime/";
	private  int runTimeThreads;
	
	
	private static GameConfig config = null;
	
	private double ucb1C = 0.5;
	private double ucb1MinimumIterations = 1;

	

	private GameConfig() {
		
	}

	public static GameConfig getInstance() {
		if(config==null) {
			Properties prop = new Properties();
			String fileName = runTimeDirectory + "config/main.config";
			InputStream is = null;
			
			config = new GameConfig();
			
			try {
				is = new FileInputStream(fileName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				prop.load(is);
				
				config.runTimeThreads = getIntProperty(prop,"runtime.threads");
				config.ucb1C = getDoubleProperty(prop,"ucb1.C");
				config.ucb1MinimumIterations = getDoubleProperty(prop,"ucb1.minimumiterations");

				
				
				
				System.out.println(config);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return config;
	}
	
	
	private static int getIntProperty(Properties prop,String name) {
		return  Integer.parseInt((prop
				.getProperty("runtime.threads").trim()));
	}
	
	private static double getDoubleProperty(Properties prop,String name) {
		return Double.parseDouble((prop
				.getProperty("runtime.threads").trim()));
	}



	public  int getRunTimeThreads() {
		return runTimeThreads;
	}

	public double getUcb1C() {
		return ucb1C;
	}

	public double getUcb1MinimumIterations() {
		return ucb1MinimumIterations;
	}

	public String getRunTimeDirectory() {
		return runTimeDirectory;
	}

	
	
	

	

}
