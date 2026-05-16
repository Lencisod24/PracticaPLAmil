(module
(data (i32.const 64) "\22\00\00\00") ;; 34
(data (i32.const 68) "#\00\00\00")   ;; 35
(export "init" (func $init))
(type $_sig_void (func ))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_i32i32ri32ri32 (func (param i32 i32)(result i32 i32)))
(start $init)
(elem $funcmap (i32.const 0) $princ)(import "runtime" "print" (func $print (type $_sig_i32)))
(import "runtime" "read" (func $read (type $_sig_ri32)))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(table $funcmap 1 1 funcref)
(global $smd i32 (i32.const 64)) ;; points to start of memory data
(memory 2000)
(global $SP (mut i32) (i32.const 0))
(global $MP (mut i32) (i32.const 0))
(global $NP (mut i32) (i32.const 131071996))
(func $reserveStack (param $size i32) (result i32)
  global.get $MP
  global.get $SP
  global.set $MP
  global.get $SP
  local.get $size
  i32.add
  global.set $SP
  global.get $SP
  global.get $NP
  i32.gt_u
  if
    i32.const 3
    call $exception
  end
)
(func $freeStack (type $_sig_void)
  global.get $MP
  global.set $SP
  global.get $MP
  i32.load
  global.set $MP
)
(func $init
;;VARIABLES GLOBALES
;;FIN VARIABLES GLOBALES
  call $princ
)
(func $princ
  i32.const 8
  call $reserveStack
  global.get $MP
  i32.store
  global.get $MP
  i32.const 4
  i32.add
  global.get $NP
	i32.const 4
  i32.sub
  global.set $NP
  global.get $SP
  global.get $NP
  i32.gt_u
  if
    i32.const 3
    call $exception
  end
  global.get $NP
  i32.store
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  i32.const 42
  i32.store
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  i32.load
call $print
  call $freeStack
)
)
