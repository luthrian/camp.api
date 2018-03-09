/*******************************************************************************
 * Copyright (C) 2018 Christopher Campbell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 * 	Christopher Campbell - all code prior and post initial release
 ******************************************************************************/
package com.camsolute.code.camp.api;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.ws.rs.ApplicationPath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;

import com.camsolute.code.camp.lib.utilities.Util;
import com.camsolute.code.camp.api.filters.AuthenticationFilter;
import com.camsolute.code.camp.api.exceptions.ExceptionHandler;

@ApplicationPath("/camp")
public class APIServicePoints extends ResourceConfig {
	private static final Logger LOG = LogManager.getLogger(APIServicePoints.class);
	private static String fmt = "[%15s] [%s]";
	

	public APIServicePoints() {
		long startTime = System.currentTimeMillis();
		String _f = null;
		String msg = null;
		if(!Util._IN_PRODUCTION) {
			_f = "[APIServicePoints]";
			msg = "====[ Configuring service points... ]====";LOG.traceEntry(String.format(fmt,(_f+">>>>>>>>>").toUpperCase(),msg));
		}
			
		//TODO: put this in a better place 
		// register required jdbc driver
		try{
			DriverManager.registerDriver(new org.mariadb.jdbc.Driver ());
		} catch (SQLException e) {
			if(!Util._IN_PRODUCTION){msg = "----[SQLExceeption! Failed to register jdbc driver DriverManager.registerDriver(new org.mariadb.jdbc.Driver())]----";LOG.info(String.format(fmt, _f,msg));}
			e.printStackTrace();
		}
		
		packages("com.camsolute.code.camp.api.servicepoints");
			
		register(AuthenticationFilter.class);
			
		register(ExceptionHandler.class);
			
			
		if(!Util._IN_PRODUCTION) {
			String time = "[ExecutionTime:"+(System.currentTimeMillis()-startTime)+")]====";
			msg = "====[APIServicePoints completed.]====";LOG.info(String.format(fmt,("<<<<<<<<<"+_f).toUpperCase(),msg+time));
		}
		
	}
}
