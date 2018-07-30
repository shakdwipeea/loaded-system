(ns clj.new.loaded-system
  (:require [clj.new.templates :refer [renderer project-name name-to-path ->files group-name]]))

(def render (renderer "loaded-system"))

(defn loaded-system
  "FIXME: write documentation"
  [name]
  (let [data {:name (project-name name)
              :group (group-name name)
              :sanitized (name-to-path name)}]
    (println "Generating a fresh 🚀 loaded-system 🚀 project.")
    (->files data
             ["deps.edn" (render "deps.edn" data)]
             ["src/{{sanitized}}/app.clj" (render "app.clj" data)]
             ["src/{{sanitized}}/app.cljs" (render "app.cljs" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["package.json" (render "package.json" data)]
             ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
             ["resources/public/index.html" (render "index.html" data)]
             [".circleci/config.yml" (render "config.yml" data)]
             [".gitignore" (render ".gitignore" data)]
             ["profiles.edn" (render "profiles.edn" data)]
             ["Readme.md" (render "Readme.md" data)])))
