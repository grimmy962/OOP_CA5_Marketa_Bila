package Exceptions;

//copied from dermot's data access layer sample
    import java.sql.SQLException;

    //excpetion class for handling dao related exceptions
    public class DaoExceptions extends SQLException
    {
        //constructor with message and exception chaining
        public DaoExceptions(String s, Exception e)
        {
            // not used
        }

        //constructor with a message
        public DaoExceptions(String aMessage)
        {
            super(aMessage);
        }
    }

