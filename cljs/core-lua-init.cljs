(ns cljs.core)

(defprotocol Object (toString [x]))

;; default type
(deftype default [])
(defn default-proto-table [] (.-proto-methods cljs.core.default))
(builtins/init-meta-tables)

(defprotocol IStringBuffer (append [x str]))

(deftype StringBuffer []
  Object
  (toString [x] (table/concat x))
  IStringBuffer
  (append [x str] (builtins/array-insert x str) x))

(defn string-buffer []
  (let [sb (StringBuffer.)]
    (set! (.-length sb) 0)
    sb))

(defprotocol INode
  (inode-assoc [inode shift hash key val added-leaf?])
  (inode-without [inode shift hash key])
  (inode-lookup [inode shift hash key not-found])
  (inode-find [inode shift hash key not-found])
  (inode-seq [inode])
  (ensure-editable [inode e])
  (inode-assoc! [inode edit shift hash key val added-leaf?])
  (inode-without! [inode edit shift hash key removed-leaf?])
  (kv-reduce [inode f init]))

(defprotocol IHashCollisionNode
  (ensure-editable-array [inode e count array]))

(defprotocol IBitmapIndexedNode
  (edit-and-remove-pair [inode e bit i]))

(defprotocol ITransientHashMap
  (tconj! [tcoll o])
  (tassoc! [tcoll k v])  
  (twithout! [tcoll k])
  (tpersistent! [tcoll]))

(defprotocol IRBNode
  (add-left [node ins])
  (add-right [node ins])
  (remove-left [node del])
  (remove-right [node del])
  (blacken [node])
  (redden [node])
  (-balance-left [node parent])
  (-balance-right [node parent])
  (nreplace [node key val left right])
  (kv-reduce [node f init]))

(defprotocol IPersistentTreeMap
  (entry-at [coll k]))

(defprotocol IString
  (find-last [str substr]))