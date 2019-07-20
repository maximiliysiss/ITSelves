namespace TaskAPI.Models
{
    public class HistoryTask : Task
    {
        public Task OriginalTask { get; set; }
    }
}