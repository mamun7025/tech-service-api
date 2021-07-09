package geotrack

import auth.User
import grails.gorm.transactions.Transactional

@Transactional
class LocationTrackService {

    def serviceMethod() {

    }

    LinkedHashMap<User, Double>getNearestPeoples(userLocationList, Double clientLat, Double clientLon, Integer numOfPeople){

        if(numOfPeople == null) numOfPeople = 10;
        Map<User, Double> peopleDistMap = new HashMap<>()
        LinkedHashMap<User, Double> nearestPeoplesMap = new LinkedHashMap<>();

        if(clientLat == null) return nearestPeoplesMap
        if(clientLon == null) return nearestPeoplesMap

        for (UserLocation userLocation : userLocationList) {
            User user = userLocation.user
            Double techLat = userLocation.latitude
            Double techLon = userLocation.longitude
            // long timestamp = userLocation.getTimestamp();
            // System.out.println(user.getUsername() +" : "+ techLat + " / " + techLon + " timestamp:" + timestamp);
            double dist = distance(clientLat, clientLon, techLat, techLon, "K")
            peopleDistMap.put(user, dist)
        }

        // sorting map
        ArrayList<User> sortedPeoplesList = new ArrayList<>()

        peopleDistMap = peopleDistMap.sort { it.value }
        peopleDistMap.each{k,v->
            sortedPeoplesList.add(k)
        }

        for (int counter = 0; counter < sortedPeoplesList.size(); counter++) {
            if( counter > numOfPeople ) break;
            User people = sortedPeoplesList.get(counter)
            Double distance = peopleDistMap.get(people)
            nearestPeoplesMap.put(people, distance)
//            System.out.println(people.getId() + " : " + people.getUsername() + ": " + distance);
        }
        // System.out.println(nearestPeoplesMap);
        return nearestPeoplesMap

    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;

        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            if (unit.equals("K")) {
                dist = dist * 1.609344;
            } else if (unit.equals("N")) {
                dist = dist * 0.8684;
            }
            return (dist);
        }
    }

}
