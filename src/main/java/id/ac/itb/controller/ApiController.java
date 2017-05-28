package id.ac.itb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.ac.itb.model.Beacon;
import id.ac.itb.model.Building;
import id.ac.itb.model.Floor;
import id.ac.itb.model.Ibeacon;
import id.ac.itb.model.Position;
import id.ac.itb.model.Room;
import id.ac.itb.utils.BeaconUtils;

@RestController
@RequestMapping
public class ApiController {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(value = "/ibeacons", method = RequestMethod.GET)
	List<Ibeacon> getIbeacons() {
		List<Ibeacon> ibeacons = jdbcTemplate.query("SELECT * FROM `get_ibeacon`",
				new BeanPropertyRowMapper<Ibeacon>(Ibeacon.class));
		return ibeacons;
	}

	@RequestMapping(value = "/buildings", method = RequestMethod.GET, params = { "search" })
	List<Building> getBuildings(@RequestParam String search) {
		search = "%" + search + "%";
		List<Building> buildings = jdbcTemplate.query(
				"SELECT * FROM `get_building` WHERE `title` LIKE ? OR `description` LIKE ?",
				new String[] {search, search},
				new BeanPropertyRowMapper<Building>(Building.class));
		return buildings;
	}

	@RequestMapping(value = "/floors/{id}", method = RequestMethod.GET)
	Floor getFloors(@PathVariable Integer id) {
		try{
			Floor floor = jdbcTemplate.queryForObject(
					"SELECT * FROM `get_floor` WHERE `id` = ?",
					new Object[] {id},
					new BeanPropertyRowMapper<Floor>(Floor.class));
			return floor;
		} catch (EmptyResultDataAccessException e){
			return null;
		}
	}
	
	@RequestMapping(value = "/rooms", method = RequestMethod.GET, params = { "search" })
	List<Room> getRooms(@RequestParam String search) {
		search = "%" + search + "%";
		List<Room> rooms = jdbcTemplate.query(
				"SELECT * FROM `get_room` WHERE `title` LIKE ? OR `description` LIKE ?",
				new String[] {search, search},
				new BeanPropertyRowMapper<Room>(Room.class));
		return rooms;
	}

	@RequestMapping(value = "/floors/{id}/rooms", method = RequestMethod.POST)
	List<Room> trialteration(@RequestBody Beacon[] beacons, @PathVariable Integer id) {
		Position position = BeaconUtils.trilateration(beacons);
		List<Room> rooms = jdbcTemplate.query(
				"SELECT * FROM `get_room` WHERE id_floor = ? AND x1 < ? AND x2 > ? AND y1 < ? AND x2 > ?",
				new Object[] {id, position.getX(), position.getX(), position.getY(), position.getY()},
				new BeanPropertyRowMapper<Room>(Room.class));
		for (Room room : rooms){
			room.setPosition(position);
		}
		return rooms;
	}
}
