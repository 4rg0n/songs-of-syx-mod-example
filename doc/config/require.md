# Requires

Requires are a list of conditions, which must match for something to be unlocked.
Example for requiring a minimum of 3500 overall employees:
```
REQUIRES: {
	GREATER: {
		WORKFORCE: 3500,
	},
},
```
```
REQUIRES: {
	<COMPARATOR>: {
		<VALUE>: 3500,
	},
},
```

## Comparators

| \<COMPARATOR\> | Description                                                      |
|----------------|------------------------------------------------------------------|
| GREATER        | `>` Whether a numeric value is greater than something.           |
| GREATERE       | `>=` Whether a numeric value is greater or equal than something. |
| LESS           | `<` Whether a numeric value is lesser than something.            |
| LESSE          | `<=` NOT IMPLEMENTED =(                                          |
| EQUAL          | `==` Whether a numeric value is equal to something.              |
| NEQUAL         | `!=` Whether a numeric value is not equal to something.          |

## Values

Comparator \<VALUE\>s are partially hard coded and partially generated from available assets like resources or rooms.
For a list of all available vanilla comparator values see: [all comparator values](../res/comparator_values_all.md)