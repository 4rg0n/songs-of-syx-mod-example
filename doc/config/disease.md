# Disease

`data/assets/init/disease`

| Key                | Required | Default | Min  | Max  | Description                                                                                                                        | Example                                                                          |
|--------------------|----------|---------|------|------|------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| EPIDEMIC           | no       | false   | none | none | Whether it can occur as an epidemic.                                                                                               |                                                                                  |
| REGULAR            | no       | false   | none | none | Whether it can occur as an regular disease.                                                                                        |                                                                                  |
| INFECTION_DAYS     | yes      | none    | 1    | 100  | How many in-game days until the disease breaks out for a citizen.                                                                  |                                                                                  |
| FATALITY_RATE      | yes      | none    | 0.0  | 1.0  | Chance of being deadly.                                                                                                            |                                                                                  |
| SPREAD             | yes      | none    | 0.0  | 1.0  | Chance of spreading to others.                                                                                                     |                                                                                  |
| COLOR              | yes      | none    | none | none | Skin color of citizens with this disease.                                                                                          | `255_255_255` or<br/> `{R: 255, G: 255, B: 255, }`                               |
| OCCURRENCE_CLIMATE | yes      | none    | 0.0  | 1.0  | Chance for the disease to break out in a specific climate.<br/>Possible climates can be found in `assets/init/config/CLIMATE.txt`. | `{ COLD: 0.8, TEMPERATE: 1.0, HOT: 0.0, }`</br>or `{ *: 0.5, }` for all climates |
| OCCURRENCE_TERRAIN | yes      | none    | 0.0  | 1.0  | Chance for the disease to break out in a specific terrain.<br/>Possible terrains can be found in `assets/init/config/TERRAIN.txt`. | `{ FOREST: 0.5, WET: 0.5, NONE: 1.0, }`</br>or `{ *: 0.2, }` for all terrains    |
