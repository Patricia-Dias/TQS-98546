# QUESTIONS

> lab3_1

# a)
Some AssertJ chaining examples:
```
assertThat( found ).isEqualTo(alex);

assertThat(found).extracting(Employee::getName).containsOnly("bob");

assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```

# b)
Mockito example
```
    @Mock( lenient = true)
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }
```

# c)
@Mock
This annotation is a shortcut to call the Mockito.mock() method, which creates a mock object of a class/interface. But, unlike the mock(), we need to enable Mockito annotations.


@MockBean
We can use this annotation to add mock objects to the Spring application. The mock will replace any existing bean of the same type in the application context. @MockBean is iseful in integration tests where a bean (like an external service) needs to be mocked.

[reference](https://www.baeldung.com/java-spring-mockito-mock-mockbean)

# d)
To simulate the database behavior, with a simplier database.

# e)

Test C

Test D

Test E