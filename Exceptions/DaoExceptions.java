package Exceptions;

//copied from derek's data access layer sample
    import java.sql.SQLException;

    public class DaoExceptions extends SQLException
    {
        public DaoExceptions(String s, Exception e)
        {
            // not used
        }

        public DaoExceptions(String aMessage)
        {
            super(aMessage);
        }
    }

