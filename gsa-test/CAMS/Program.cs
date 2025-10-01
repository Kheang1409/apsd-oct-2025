using Microsoft.AspNetCore.Builder;
using Microsoft.EntityFrameworkCore;
using CAMS.Persistence;
using CAMS.Repositories;
using CAMS.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container
builder.Services.AddControllers()
    .AddJsonOptions(options =>
    {
        options.JsonSerializerOptions.ReferenceHandler = System.Text.Json.Serialization.ReferenceHandler.IgnoreCycles;
        options.JsonSerializerOptions.DefaultIgnoreCondition = System.Text.Json.Serialization.JsonIgnoreCondition.WhenWritingNull;
    });

// Add Entity Framework Core
builder.Services.AddDbContext<AppDBContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));

// Register repositories
builder.Services.AddScoped<IAccountRepository, AccountRepository>();

// Register services
builder.Services.AddScoped<IAccountService, AccountService>();

// Add CORS services and allow all origins, headers, and methods
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowAll",
        policy => policy.AllowAnyOrigin()
                        .AllowAnyHeader()
                        .AllowAnyMethod());
});

var app = builder.Build();

// Use the CORS policy
app.UseCors("AllowAll");

// Map controllers
app.MapControllers();

// Example endpoint
app.MapGet("/", () => "CORS is enabled for all!");

app.Run();