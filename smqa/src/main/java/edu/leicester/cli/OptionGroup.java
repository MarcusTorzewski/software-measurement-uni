package edu.leicester.cli;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A group of mutually exclusive options.
 */
public class OptionGroup implements Serializable
{
    /** The serial version UID. */
    private static final long serialVersionUID = 1L;

    /** hold the options */
    private final Map<String, Option> optionMap = new LinkedHashMap<>();

    /** the name of the selected option */
    private String selected;

    /** specified whether this group is required */
    private boolean required;

    /**
     * Add the specified <code>Option</code> to this group.
     *
     * @param option the option to add to this group
     * @return this option group with the option added
     */
    public OptionGroup addOption(final Option option)
    {
        // key   - option name
        // value - the option
        optionMap.put(option.getKey(), option);

        return this;
    }

    /**
     * @return the names of the options in this group as a
     * <code>Collection</code>
     */
    public Collection<String> getNames()
    {
        // the key set is the collection of names
        return optionMap.keySet();
    }

    /**
     * @return the options in this group as a <code>Collection</code>
     */
    public Collection<Option> getOptions()
    {
        // the values are the collection of options
        return optionMap.values();
    }

    /**
     * Set the selected option of this group to <code>name</code>.
     *
     * @param option the option that is selected
     * @throws IllegalStateException if an option from this group has
     * already been selected.
     */
    public void setSelected(final Option option) throws IllegalStateException
    {
        if (option == null)
        {
            // reset the option previously selected
            selected = null;
            return;
        }

        // if no option has already been selected or the
        // same option is being reselected then set the
        // selected member variable
        if ((selected != null) && !selected.equals(option.getKey())) {
            throw new IllegalStateException();
        }
        selected = option.getKey();
    }

    /**
     * @return the selected option name
     */
    public String getSelected()
    {
        return selected;
    }

    /**
     * @param required specifies if this group is required
     */
    public void setRequired(final boolean required)
    {
        this.required = required;
    }

    /**
     * Returns whether this option group is required.
     *
     * @return whether this option group is required
     */
    public boolean isRequired()
    {
        return required;
    }

    /**
     * Returns the stringified version of this OptionGroup.
     *
     * @return the stringified representation of this group
     */
    @Override
    public String toString()
    {
        final StringBuilder buff = new StringBuilder();

        final Iterator<Option> iter = getOptions().iterator();

        buff.append("[");

        while (iter.hasNext())
        {
            final Option option = iter.next();

            if (option.opt != null)
            {
                buff.append("-");
                buff.append(option.opt);
            }
            else
            {
                buff.append("--");
                buff.append(option.longOpt);
            }

            if (option.description != null)
            {
                buff.append(" ");
                buff.append(option.description);
            }

            if (iter.hasNext())
            {
                buff.append(", ");
            }
        }

        buff.append("]");

        return buff.toString();
    }
}