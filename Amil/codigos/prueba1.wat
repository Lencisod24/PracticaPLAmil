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
(global $SP (mut i32) (i32.const 8))
(global $MP (mut i32) (i32.const 8))
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
  i32.const 0
  i32.const 4
  i32.store
  i32.const 4
  i32.const 7
  i32.store
;;FIN VARIABLES GLOBALES
  call $princ
)
(func $princ
  i32.const 60
  call $reserveStack
  global.get $MP
  i32.store
  global.get $MP
  i32.const 12
  i32.add
  i32.const 1
  i32.store
  global.get $MP
  i32.const 16
  i32.add
  f32.const 2.0
  f32.store
  global.get $MP
  i32.const 4
  i32.add
  i32.const 0
  i32.load
  i32.store
  global.get $MP
  i32.const 8
  i32.add
  i32.const 3
  i32.store
  global.get $MP
  i32.const 4
  i32.add
  i32.load
call $print
  global.get $MP
  i32.const 20
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.add
  i32.const 2
  i32.rem_s
  i32.store
  global.get $MP
  i32.const 24
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.sub
  i32.store
  global.get $MP
  i32.const 28
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.mul
  i32.store
  global.get $MP
  i32.const 32
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.gt_s
  i32.store
  global.get $MP
  i32.const 36
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.lt_s
  i32.store
  global.get $MP
  i32.const 40
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  i32.const 0
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.sub
  i32.eq
  i32.store
  global.get $MP
  i32.const 44
  i32.add
  i32.const 1
  i32.eqz
  i32.const 0
  i32.and
  i32.store
  global.get $MP
  i32.const 48
  i32.add
  i32.const 1
  i32.const 0
  i32.or
  i32.store
  global.get $MP
  i32.const 52
  i32.add
  i32.const 0
  i32.store
  global.get $MP
  i32.const 56
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.add
  global.get $MP
  i32.const 4
  i32.add
  i32.load
  global.get $MP
  i32.const 8
  i32.add
  i32.load
  i32.sub
  i32.mul
  i32.store
  call $freeStack
)
)
