using System;
using System.Collections.Generic;
using System.Text;

namespace CommonLibrary
{
    public enum TaskStatus
    {
        InConsideration,
        InProgress,
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

    public class GetRus
    {
        public static Dictionary<Category, string> RuCategory = new Dictionary<Category, string>
        {
            { Category.Clean, "Уборка"}, { Category.Repair, "Ремонт"}
        };
        public static Dictionary<Rate, string> RuRate = new Dictionary<Rate, string>
        {
            { Rate.One, "Один"}, {Rate.Two, "Два"}, { Rate.Three, "Три" }, { Rate.Four, "Четыре" },{ Rate.Five, "Пять" }
        };
        public static Dictionary<TaskStatus, string> RuTaskStatus = new Dictionary<TaskStatus, string>
        {
            {TaskStatus.Confirmed, "Выполнено"}, {TaskStatus.Declined, "Отклонено"}, { TaskStatus.InConsideration, "На рассмотрении" }, { TaskStatus.InProgress, "В прогрессе" },{ TaskStatus.Mark, "Оценивание" }
        };
    }
}
