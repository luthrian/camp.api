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
package com.camsolute.code.camp.api.servicepoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.camsolute.code.camp.lib.data.CampRest;
import com.camsolute.code.camp.lib.dao.rest.ContactDetailsServicePointInterface;

@Path(CampRest.ContactDetails.Prefix)
public class ContactDetailsAPI implements ContactDetailsServicePointInterface {

	@Path(CampRest.DaoService.LOAD_BY_ID) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadById(int id) {
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_EMAIL) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByEmail(String emailAddress) {
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_SKYPE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadBySkype(String skype) {
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_TELEPHONE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByPhone(String phoneNumber) {
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_MOBILE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByMobile(String mobileNumber) {
		return json;
	}

	@Path(CampRest.ContactDetailsDaoService.LOAD_BY_MISC) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadByMisc(String misc) {
		return json;
	}

	@Path(CampRest.DaoService.CREATE_CUSTOMER_DETAILS) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String create(String email, String mobile, String telephone, String skype, String misc) {
		return json;
	}

	@Path(CampRest.DaoService.SAVE) @POST @Consumes(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String save(String c) {
		return json;
	}

	@Path(CampRest.DaoService.SAVE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String saveList(String cl) {
		return json;
	}

	@Path(CampRest.DaoService.UPDATE) @POST @Consumes(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String update(String c) {
		return json;
	}

	@Path(CampRest.DaoService.UPDATE_LIST) @POST @Consumes(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String updateList(String cl) {
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdates(String businessKey, String target) {
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATES_KEY) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByKey(String businessKey) {
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATES_TARGET) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdatesByTarget(String target) {
		return json;
	}

	@Path(CampRest.DaoService.LOAD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String loadUpdate(String customerBusinessId, String businessKey, String target) {
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addToUpdates(String customerBusinessId, String businessKey, String target) {
		return json;
	}

	@Path(CampRest.DaoService.ADD_UPDATES) @POST @Consumes(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String addListToUpdates(String customerList, String businessKey, String target) {
		return json;
	}

	@Path(CampRest.DaoService.DELETE_ALL_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteAllFromUpdates(String businessKey, String target) {
		return json;
	}

	@Path(CampRest.DaoService.DELETE_KEY_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdatesByKey(String businessKey) {
		return json;
	}

	@Path(CampRest.DaoService.DELETE_TARGET_UPDATES) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdatesByTarget(String target) {
		return json;
	}

	@Path(CampRest.DaoService.DELETE_UPDATE) @GET @Produces(MediaType.APPLICATION_JSON)
	@Override
	public String deleteFromUpdates(String customerBusinessId, String businessKey, String target) {
		return json;
	}

}
