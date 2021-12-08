package edu.leicester.cli;

/**
 * Contains useful helper methods for classes within this package.
 */
final class Utils {

    /**
     * Remove the hyphens from the beginning of <code>str</code> and
     * return the new String.
     *
     * @param str The string from which the hyphens should be removed.
     *
     * @return the new String.
     */
    static String stripLeadingHyphens(final String str)
    {
        if (str == null)
        {
            return null;
        }
        if (str.startsWith("--"))
        {
            return str.substring(2);
        }
        if (str.startsWith("-"))
        {
            return str.substring(1);
        }

        return str;
    }

    /**
     * Remove the leading and trailing quotes from <code>str</code>.
     * E.g. if str is '"one two"', then 'one two' is returned.
     *
     * @param str The string from which the leading and trailing quotes
     * should be removed.
     *
     * @return The string without the leading and trailing quotes.
     */
    static String stripLeadingAndTrailingQuotes(String str)
    {
        final int length = str.length();
        if (length > 1 && str.startsWith("\"") && str.endsWith("\"") && str.substring(1, length - 1).indexOf('"') == -1)
        {
            str = str.substring(1, length - 1);
        }

        return str;
    }

    /**
     * Validates whether <code>opt</code> is a permissible Option
     * shortOpt.  The rules that specify if the <code>opt</code>
     * is valid are:
     *
     * <ul>
     *  <li>a single character <code>opt</code> that is either
     *  ' '(special case), '?', '@' or a letter</li>
     *  <li>a multi character <code>opt</code> that only contains
     *  letters.</li>
     * </ul>
     * <p>
     * In case {@code opt} is {@code null} no further validation is performed.
     *
     * @param opt The option string to validate, may be null
     * @throws IllegalArgumentException if the Option is not valid.
     */
    static void validateOption(final String opt) throws IllegalArgumentException
    {
        // if opt is NULL do not check further
        if (opt == null)
        {
            return;
        }

        // handle the single character opt
        if (opt.length() == 1)
        {
            final char ch = opt.charAt(0);

            if (!isValidOpt(ch))
            {
                throw new IllegalArgumentException("Illegal option name '" + ch + "'");
            }
        }

        // handle the multi character opt
        else
        {
            for (final char ch : opt.toCharArray())
            {
                if (!isValidChar(ch))
                {
                    throw new IllegalArgumentException("The option '" + opt + "' contains an illegal "
                            + "character : '" + ch + "'");
                }
            }
        }
    }

    /**
     * Returns whether the specified character is a valid Option.
     *
     * @param c the option to validate
     * @return true if <code>c</code> is a letter, '?' or '@', otherwise false.
     */
    private static boolean isValidOpt(final char c)
    {
        return isValidChar(c) || c == '?' || c == '@';
    }

    /**
     * Returns whether the specified character is a valid character.
     *
     * @param c the character to validate
     * @return true if <code>c</code> is a letter.
     */
    private static boolean isValidChar(final char c)
    {
        return Character.isJavaIdentifierPart(c);
    }
}