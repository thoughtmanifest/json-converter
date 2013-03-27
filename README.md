# json-converter

A Clojure library designed to convert simple, flat, key/value json records into a single csv with shared structure.

## Usage

java -jar json-converter-0.1.0-standalone.jar -i "input-file.json" -o "result-file.csv"

The expected input is a file of json records where you want all fields to generate a single structure csv file.

For example a file with the following two lines:

        {"key1": "value1a", "key2": "value2"}
        {"key1": "value1b", "key3": "value3"}

Transforms into a csv with the following 3 lines:

        "key1","key2","key3"
        "value1a","value2",nil
        "value1b",nil,"value3"

## License

Copyright © 2013 ThoughtManifest

Distributed under the Eclipse Public License, the same as Clojure.
