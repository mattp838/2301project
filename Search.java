import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * The Search class is a utility class that searches the database for Movies,
 * or Members.  It can also retrieve specific movies or members given their
 * unique barcode numbers.
 * 
 * TODO: interface based on the class diagrams
 * TODO: decide if this should be static
 * TODO: go through and make sure all the Class names and attributes and method
 * calls match.
 * 
 * Last updated:
 * 25 Mar
 *
 * @author Mitch
 * @version 0.1
 *
 */
public class Search
{

    
    /**
     * This enumeration represents all the possible genres of movies that are
     * carried by the video store.
     * TODO: decide maybe this is standalone enum, or mabye it goes in Movie class
     */
    public enum Genre
    {
        ACTION, COMEDY, DRAMA, FAMILY, HORROR, ROMANCE, SUSPENSE, WESTERN
    }


    /**
     * TODO: problem with a 10 digit phone number being too large for Integer.
     * solution: made it a string
     * 
     * searchMembers takes the name of a member or his phone number and
     * searches the database for matches.  A maximum of one parameter may be
     * null.  A list of MemberAccounts that match are returned.
     * @param lastName the name of the customer.  Not case sensitive.
     * @param phoneNum the 9 digit phone number of the customer.
     * @return a list of MemberAccounts that match the search criteria, or
     * null if no matching accounts could be found
     * @throws SQLException if a connection with the database cannot be made
     * @throws SanitizerException if the passed parameters contain SQL queries
     * TODO: update when I understand Sanitizer better
     * TODO: verify assumption that MemberAccount can be constructed with only
     * the member ID.
     */
    public static ArrayList<MemberAccount>
            searchMembers(String lastName, String phoneNum)
            throws SQLException, SanitizerException
    {
        if (lastName == null && phoneNum == null)
        {
            return null;
        }
        ArrayList<MemberAccount> matchingMembers
                    = new ArrayList<MemberAccount>();
        Connection connection = DataSource.getConnection();
        try
        {
            String query = generateMemberQuery(lastName, phoneNum);
            PreparedStatement statement = connection.prepareStatement(query);
            if (lastName != null)
            {
                statement.setString(1, lastName);
            }
            
            Sanitizer.sanitize(phoneNum);
            ResultSet result = statement.executeQuery(query);

            
            while (result.next())
            {
                int matchingAccountID = result.getInt("accountID");
                matchingMembers.add(new MemberAccount(matchingAccountID));
            }
            result.close();
        } // end try
        finally
        {
            connection.close();
        }
        if (matchingMembers.size() > 0)
        {
            return matchingMembers;
        }
        else
        {
            return null;
        }
    }
    
    
    
    /**
     * Helper function for searchMember.  Generates an SQL query.
     * @pre the case where lastName and phoneNum should be filtered out before
     * passing it to this.  However, there is a redundant check anyways.
     * @param lastName member's last name to search for
     * @param phoneNum member's phone number
     * @return a string containg the query or null if lastName and phoneNum are
     * both null
     */
    private static String generateMemberQuery(String lastName, String phoneNum)
    {
        if (lastName == null && phoneNum == null)
        {
            return null;
        }

        String query = "SELECT accountID FROM Customer WHERE ";
        String nameQuery = "lName = ?";
        String phoneNumQuery = "phone = ";

        if (lastName != null && phoneNum == null)
        {
            query += nameQuery;
        }
        else if (phoneNum != null && lastName == null)
        {          
            phoneNumQuery += phoneNum;
            query += phoneNumQuery;      
        }
        else
        {     
            phoneNumQuery += phoneNum;
            query += nameQuery + " AND " + phoneNumQuery;
        }
        return query;
    }



    /**
     * This method retrieves the member account with the provided member ID from
     * the database.  Depends on both the MemberAccount class and the member 
     * table in the database.
     *
     * @param memberID the unique identifying number of the member
     * @return the member's account, or null if the member ID did not match any
     * records
     * @throws SQLException if a connection with the database cannot be made
     */
    public static MemberAccount getMember(int memberID)
            throws SQLException
    {
        Connection connection = DataSource.getConnection();
        try
        {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Customer WHERE accountID = "
                    + memberID;
            ResultSet result = statement.executeQuery(query);
            if (result.next())
            {
                String firstName = result.getString("fName");
                String lastName = result.getString("lName");
                String address = result.getString("address");
                String driversLicenseNum = result.getString("dlsin");
                // TODO: type of phoneNum is not set in stone. might be BigInteger
                String phoneNum = result.getString("phone");
                String altPhoneNum = result.getString("altPhone");
                // TODO: need comments about customer


                result.close();
                return new MemberAccount(memberID, firstName, lastName, address,
                        driversLicenseNum, phoneNum, altPhoneNum);
            } // end if
            else
            {
                result.close();
                return null;
            }
        }// end try
        finally
        {
            connection.close();
        }
    }


    /**
     * This method searches the database for a GeneralMovie matching the passed
     * search criteria.  At least one of the fields must be filled in.
     * The main purpose of this search is to give the searcher an idea of what
     * movies are carried by the store and their availability, without
     * overwhelming him with information about the individual copies.
     *
     * This differs from searchRentals because the searchRentals will return
     * a list of each individual RentalMovies wheras this method only returns
     * the general movie information shared by all copies with the same SKU.
     *
     * @param title The title of the movie.
     * @param actor An actor who was in the movie
     * @param director The director of the movie
     * @return a list of movies matching the search criteria.  May return null
     * if no matches could be found.
     * @throws SQLException if a connection with the database cannot be made
     * @throws SanitizerException if the passed parameters contain SQL queries
     */
    public static ArrayList<GeneralMovie> searchMovies(
            String title,
            String actor,
            String director)
            throws SQLException, SanitizerException
    {
        ResultSet result 
                = searchMoviesGetSQLResult(title, actor, director);
        ArrayList<GeneralMovie> searchResults = new ArrayList<GeneralMovie>();
        while (result.next())
        {
            searchResults.add(
                    new GeneralMovie(result.getString("physicalVideo.SKU")));
        }
        result.close();

        if (searchResults.size() == 0)
        {
            return null;
        }
        else
        {
            return searchResults;
        }
    }



    /**
     * This method queries the database for a movie matching the passed
     * search criteria.  It returns the results of the query.
     *
     * @param title The title of the movie
     * @param actor An actor who was in the movie
     * @param director The director of the movie
     * @return the results of an SQL query for all movies or rental movies that
     * match the search criteria
     * @throws SQLException
     * @throws SanitizerException
     * TODO: update when sanitizer is better understood
     * TODO: verify assumption that GeneralMovie can be constructed with only the infoID
     * TODO: verify assumption that GeneralMovie infoID will be a String
     * TODO: verify that RentalMovie will inherit from Movie
     */
    private static ResultSet searchMoviesGetSQLResult(
            String title,
            String actor,
            String director)
            throws SQLException, SanitizerException
    {
        String query = generateMovieQuery(title, actor, director);
        if (query == null)
        {
            return null;
        }

        String[] searchTerms = {title, actor, director};
        for (String term : searchTerms)
        {
            Sanitizer.sanitize(term);
        }

        Connection connection = DataSource.getConnection();
        try
        {
            PreparedStatement statement = connection.prepareStatement(query);
            int numTerms = 3; // depends on number of parameters
            int parameterIndex = 1; // SQL starts numbering indecies from 1
            for (int i = 0; i < numTerms; i++)
            {
                if (searchTerms[i] != null)
                {
                    statement.setString(parameterIndex, searchTerms[i]);
                    parameterIndex++;
                }
            } // end for

            ResultSet result = statement.executeQuery();
            return result;
            
        } // end try
        finally
        {
            connection.close();
        }

    }



    /**
     * This is a helper method for searchVideos.  It generates an SQL query
     * of the form:
     * SELECT physicalVideo.SKU
     *   FROM physicalVideo, videoInfo
     *   WHERE videoInfo.title = ?
     *     AND videoInfo.actors LIKE %?%
     *     AND videoInfo.director = ?
     *     AND physicalVideo.infoID = videoInfo.infoID
     * It is up to searchVideos to fill the ? placeholders in using
     * connectionObject.prepareStatement(int parameterIndex, String sql)
     * At least one of the parameters must be non-null.
     * Null parameters will not be included in the SQL query.
     * If all the parameters are null, the method returns a null string.
     * @param title the title of the movie
     * @param actor the name of one actor to appear in the movie
     * @param director the director of the movie
     * @param select the table's field name.  For example, GeneralMovie would be
     * infoID wheras RentalMovie would be barcode.
     * @return a string with an SQL query, or null if all the parameters were
     * null.
     */
    public static String generateMovieQuery(
            String title,
            String actor,
            String director)
    {
        String query = "SELECT physicalVideo.SKU " +
                "FROM videoInfo, physicalVideo " +
                "WHERE ";
        ArrayList<String> searchCriteria = new ArrayList<String>();

        // adding the query forms of the non-null parameters to the array list
        if (title != null)
        {
            searchCriteria.add("videoInfo.title = ?");
        }
        if (actor != null)
        {
            searchCriteria.add("videoInfo.actors LIKE %?%");
        }
        if (director != null)
        {
            searchCriteria.add("videoInfo.director = ?");
        }

        int numSearchCriteria = searchCriteria.size();
        if (numSearchCriteria == 0)
        {
            return null;
        }
        // generating the WHERE clause
        // if there are n non-null search criteria, then there are n-1 ANDs
        String searchCriteriaStr = searchCriteria.get(0);
        for (int i = 1; i < numSearchCriteria; i++)
        {
            searchCriteriaStr += " AND ";
            searchCriteriaStr += searchCriteria.get(i);
        }

        searchCriteriaStr += " AND physicalVideo.infoID = videoInfo.infoID";

        // putting it all together
        query += searchCriteriaStr;

        return query;
    }



    /**
     * This method retrieves the movie with the provided sku from the database.
     * If the length of the barcode ID is more than the SKU length + the
     * rentalID length, they are separated and the movied previewed is found
     * using the join of the video and videorental
     *
     * This method is used when the caller knows the exact barcode number.
     * If this is not the case, use the search method.
     *
     * @param barcodeID the unique identifying number of the movie
     * @return the movie that corresponds to the barcodeID
     * TODO: need to understand the GeneralMovie class better
     */
    public static GeneralMovie previewVideo(String barcodeID)
    {
        return null;
        // TODO: implement this STUB
    }



    /**
     * This method searches the database for a rental movie matching the search
     * criteria.  At least one of the fields must not be null.  This differs
     * from the searchVideo method by finding every single rental copy
     * available.  Therefore it will not search movies that are not carried by
     * the store.
     *
     * @param title the title of the movie
     * @param actor an actor in the movie
     * @param director the director of the movie
     * @param memberID the member who is currently renting this
     * @return a list of videos that match the search criteria, or null if
     * no matching videos could be found.
     */
    public static ArrayList<RentalMovie> searchRentals(
            String title,
            String actor,
            String director,
            Integer memberID)
    {
        return null;
        // TODO: implement this STUB
    }



    /**
     * This method lets the caller see all movies corresponding to the specified
     * genre.
     * @param genre the genre of movie that we should view
     * @return a list of all videos in that genre
     */
    public static ArrayList<GeneralMovie> browse(Genre genre)
    {
        return null;
        // TODO: implement this STUB
    }


}

