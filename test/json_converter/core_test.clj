(ns json-converter.core-test
  (:use clojure.test
        json-converter.core))

(def input-seq '({"key1" "value1a" "key2" "value2"}
                 {"key1" "value1b" "key3" "value3"}))

(def headers '("key1" "key2" "key3"))

(def rows '(("\"value1a\"" "\"value2\"" "nil")
            ("\"value1b\"" "nil" "\"value3\"")))

(deftest get-headers
  (testing "input returns seq of header keys shared between json records"
    (is (= headers
           (#'json-converter.core/get-headers input-seq)))))

(deftest get-rows
  (testing "input returns seq of seqs of values with nil in place of non-existent keys"
    (is (= rows
           (#'json-converter.core/get-rows headers input-seq)))))

(deftest format-output
  (testing "headers + rows = csv format seq"
    (is (= '("\"key1\",\"key2\",\"key3\"\n"
             "\"value1a\",\"value2\",nil\n"
             "\"value1b\",nil,\"value3\"\n")
           (#'json-converter.core/format-output headers rows)))))
