using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary
{
    public enum TaskStatus
    {
        InProgress,
        InConsideration,
        Mark,
        Confirmed,
        Declined
    }

    public enum Category
    {
        Clean,
        Repair
    }

    public enum Rate
    {
        One,
        Two,
        Three,
        Four,
        Five
    }
}
